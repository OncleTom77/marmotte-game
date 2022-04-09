package com.fouan.player.strategy;

import com.fouan.card.Card;
import com.fouan.card.DeckWithDiscard;

import java.util.List;

public abstract class PlayerStrategy {

    public boolean playsTurn(List<Card> knownCards, List<Card> unknownCards, DeckWithDiscard deckWithDiscard) {
        // copy data

        executes(knownCards, unknownCards, deckWithDiscard);

        // check valid turn (a card has been drawn from deck or discard and one has been discarded)

        // should stop game at the end of round
        return stopGame(knownCards, unknownCards, deckWithDiscard);
    }

    protected abstract void executes(List<Card> knownCards, List<Card> unknownCards, DeckWithDiscard deckWithDiscard);

    public abstract boolean stopGame(List<Card> knownCards, List<Card> unknownCards, DeckWithDiscard deckWithDiscard);

    protected void exchangeUnknownCardWithCard(List<Card> knownCards, List<Card> unknownCards, Card drawnCard, DeckWithDiscard deckWithDiscard) {
        Card firstUnknownCard = unknownCards.get(0);
        unknownCards.remove(firstUnknownCard);
        knownCards.add(drawnCard);
        deckWithDiscard.discard(firstUnknownCard);
    }

    protected void exchangeKnownCardWithCard(List<Card> knownCards, Card cardToDiscard, Card drawnCard, DeckWithDiscard deckWithDiscard) {
        int cardPosition = knownCards.indexOf(cardToDiscard);
        knownCards.set(cardPosition, drawnCard);
        deckWithDiscard.discard(cardToDiscard);
    }
}
