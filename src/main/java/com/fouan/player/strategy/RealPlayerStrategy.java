package com.fouan.player.strategy;

import com.fouan.card.Card;
import com.fouan.card.Deck;
import com.fouan.card.Discard;

import java.util.List;

public class RealPlayerStrategy extends PlayerStrategy {
    @Override
    public void executes(List<Card> knownCards, List<Card> unknownCards, Deck deck, Discard discard) {
    }

    @Override
    public boolean stopGame(List<Card> knownCards, List<Card> unknownCards, Deck deck, Discard discard) {
        return false;
    }
}
