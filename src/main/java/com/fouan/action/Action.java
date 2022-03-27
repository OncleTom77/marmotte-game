package com.fouan.action;

import com.fouan.card.Deck;
import com.fouan.card.Discard;

public interface Action {
    void execute(Deck deck, Discard discard);
}
