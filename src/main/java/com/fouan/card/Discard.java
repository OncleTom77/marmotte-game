package com.fouan.card;

import com.fouan.game.DiscardObserver;

import java.util.*;

public class Discard {
    private final Deque<Card> discard;
    private final Set<DiscardObserver> discardObservers;

    public Discard() {
        discard = new ArrayDeque<>();
        discardObservers = new HashSet<>();
    }

    public void addObserver(DiscardObserver observer) {
        discardObservers.add(observer);
    }

    public Card getLast() {
        return discard.peek();
    }

    public Card drawLast() {
        return discard.pop();
    }

    public void add(Card card) {
        discard.push(card);
        discardObservers.forEach(discardObserver -> discardObserver.notify(card));
    }

    public void clear() {
        discard.clear();
    }
}
