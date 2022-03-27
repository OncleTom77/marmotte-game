package com.fouan.player.strategy;

import com.fouan.card.Card;
import com.fouan.card.Deck;
import com.fouan.card.Discard;

import java.util.List;

public abstract class PlayerStrategy {

    public void playsTurn(List<Card> knownCards, List<Card> unknownCards, Deck deck, Discard discard) {
        // copy data

        executes(knownCards, unknownCards, deck, discard);

        // check valid turn (a card has been drawn from deck or discard and one has been discarded)
    }

    protected abstract void executes(List<Card> knownCards, List<Card> unknownCards, Deck deck, Discard discard);

    protected void exchangeWithDiscard(Discard discard, List<Card> cards, Card cardToDiscard) {
        int cardPosition = cards.indexOf(cardToDiscard);
        cards.set(cardPosition, discard.drawLast());
        discard.add(cardToDiscard);
    }
}
