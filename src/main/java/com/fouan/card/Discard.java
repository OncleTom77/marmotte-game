package com.fouan.card;

import com.fouan.game.DiscardObserver;

import java.util.*;

public class Discard {
    private final Deque<Card> cards;
    private final Set<DiscardObserver> discardObservers;

    public Discard() {
        cards = new ArrayDeque<>();
        discardObservers = new HashSet<>();
    }

    public void addObserver(DiscardObserver observer) {
        discardObservers.add(observer);
    }

    public Card getLast() {
        return cards.peek();
    }

    public Card drawLast() {
        Card card = cards.pop();
        System.out.println(card + " has been drawn from discard");
        return card;
    }

    public void add(Card card) {
        System.out.println(card + " has been discarded");
        cards.push(card);
        discardObservers.forEach(discardObserver -> discardObserver.notify(card));
    }

    public List<Card> empty(boolean keepLast) {
        List<Card> copy = new ArrayList<>(this.cards);
        cards.clear();
        if (keepLast) {
            Card last = copy.remove(0);
            cards.push(last);
        }
        return copy;
    }
}
