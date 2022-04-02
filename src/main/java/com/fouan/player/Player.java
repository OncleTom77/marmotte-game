package com.fouan.player;

import com.fouan.card.Card;
import com.fouan.card.Deck;
import com.fouan.card.Discard;
import com.fouan.game.DiscardObserver;
import com.fouan.player.strategy.PlayerStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Player implements DiscardObserver {
    private final String name;
    private final PlayerStrategy strategy;
    private List<Card> knownCards;
    private List<Card> unknownCards;
    private Discard discard;
    private Deck deck;

    public Player(String name, PlayerStrategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public void initCards(List<Card> cards) {
        if (cards.size() != 4) {
            throw new IllegalArgumentException();
        }
        this.knownCards = new ArrayList<>(cards.subList(0, 2));
        this.unknownCards = new ArrayList<>(cards.subList(2, 4));
    }

    public void watch(Discard discard) {
        this.discard = discard;
        discard.addObserver(this);
    }

    public void setDeckAndDrawInitialCards(Deck deck) {
        this.deck = deck;
        initCards(deck.drawCards(4));
    }

    public boolean plays() {
        return strategy.playsTurn(knownCards, unknownCards, deck, discard);
    }

    private String printKnownCards() {
        return knownCards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(","));
    }

    private String printUnknownCards() {
        return unknownCards.stream()
                .map(card -> "?")
                .collect(Collectors.joining(","));
    }

    @Override
    public String toString() {
        return "Player " + name + ": " + printKnownCards() + " | " + printUnknownCards();
    }

    @Override
    public void notify(Card discarded) {
        // add card to discard;
        knownCards.stream()
                .filter(knownCard -> knownCard.hasSameRank(discarded))
                .findFirst()
                .ifPresent(card -> {
                    // TODO: only one card can be discarded this way
                    knownCards.remove(card);
                    discard.add(card);
                });
    }
}
