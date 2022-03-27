package com.fouan.player;

import com.fouan.card.Card;
import com.fouan.card.Deck;
import com.fouan.card.Discard;
import com.fouan.game.DiscardObserver;
import com.fouan.player.strategy.PlayerStrategy;

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
        this.knownCards = cards.subList(0, 2);
        this.unknownCards = cards.subList(2, 4);
    }

    public void watch(Discard discard) {
        this.discard = discard;
        discard.addObserver(this);
    }

    public void setDeckAndDrawInitialCards(Deck deck) {
        this.deck = deck;
        initCards(deck.drawCards(4));
    }

    @Override
    public String toString() {
        return "Player " + name + ": " + printKnownCards(knownCards) + printUnknownCards(unknownCards);
    }

    private String printKnownCards(List<Card> cards) {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(","));
    }

    private String printUnknownCards(List<Card> cards) {
        return cards.stream()
                .map(card -> "?")
                .collect(Collectors.joining(","));
    }

    public void plays() {
        strategy.playsTurn(knownCards, unknownCards, deck, discard);
    }

    @Override
    public void notify(Card discarded) {
        // add card to discard;
        knownCards.stream()
                .filter(knownCard -> knownCard.hasSameRank(discarded))
                .findFirst()
                .ifPresent(card -> {
                    // TODO: only one card can be discard this way
                    knownCards.remove(card);
                    discard.add(card);
                });
    }
}
