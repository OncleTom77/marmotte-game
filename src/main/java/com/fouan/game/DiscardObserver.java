package com.fouan.game;

import com.fouan.card.Card;

public interface DiscardObserver {
    void notify(Card discarded);
}
