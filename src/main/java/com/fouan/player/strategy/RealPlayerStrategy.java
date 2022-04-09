package com.fouan.player.strategy;

import com.fouan.card.Card;
import com.fouan.card.DeckWithDiscard;

import java.util.List;

public class RealPlayerStrategy extends PlayerStrategy {
    @Override
    protected void executes(List<Card> knownCards, List<Card> unknownCards, DeckWithDiscard deckWithDiscard) {
    }

    @Override
    public boolean stopGame(List<Card> knownCards, List<Card> unknownCards, DeckWithDiscard deckWithDiscard) {
        return false;
    }
}
