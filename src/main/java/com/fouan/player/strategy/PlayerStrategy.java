package com.fouan.player.strategy;

import com.fouan.card.Card;
import com.fouan.card.Deck;
import com.fouan.card.Discard;

import java.util.List;

public abstract class PlayerStrategy {

    public boolean playsTurn(List<Card> knownCards, List<Card> unknownCards, Deck deck, Discard discard) {
        // copy data

        executes(knownCards, unknownCards, deck, discard);

        // check valid turn (a card has been drawn from deck or discard and one has been discarded)

        // should stop game at the end of round
        return stopGame(knownCards, unknownCards, deck, discard);
    }

    protected abstract void executes(List<Card> knownCards, List<Card> unknownCards, Deck deck, Discard discard);

    public abstract boolean stopGame(List<Card> knownCards, List<Card> unknownCards, Deck deck, Discard discard);

    protected void exchangeUnknownCardWithCard(List<Card> knownCards, List<Card> unknownCards, Card drawnCard, Discard discard) {
        Card firstUnknownCard = unknownCards.get(0);
        unknownCards.remove(firstUnknownCard);
        knownCards.add(drawnCard);
        discard.add(firstUnknownCard);
    }

    protected void exchangeKnownCardWithCard(List<Card> knownCards, Card cardToDiscard, Card drawnCard, Discard discard) {
        int cardPosition = knownCards.indexOf(cardToDiscard);
        knownCards.set(cardPosition, drawnCard);
        discard.add(cardToDiscard);
    }
}
