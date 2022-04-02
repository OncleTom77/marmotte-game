package com.fouan.card;

import com.fouan.game.DiscardObserver;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

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
        Card card = discard.pop();
        System.out.println(card + " has been drawn from discard");
        return card;
    }

    public void add(Card card) {
        System.out.println(card + " has been discarded");
        discard.push(card);
        discardObservers.forEach(discardObserver -> discardObserver.notify(card));
    }

    public void clear() {
        discard.clear();
    }
}
