package com.fouan.action;

import com.fouan.card.Deck;
import com.fouan.card.Discard;

public class DrawDiscard implements Action {
    @Override
    public void execute(Deck deck, Discard discard) {
        discard.drawLast();
    }
}
