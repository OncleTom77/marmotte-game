package com.fouan.card;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    protected final LinkedList<Card> cards;

    private Deck(BiFunction<Suit, Rank, Card> supplier) {
        this.cards = new LinkedList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.addLast(supplier.apply(suit, rank));
            }
        }
    }

    public static Deck standard() {
        return new Deck(Card::new);
    }

    public static Deck marmotte() {
        return new Deck(MarmotteCard::new);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() throws EmptyDeckException {
        if (cards.isEmpty()) {
            throw new EmptyDeckException();
        }
        Card card = cards.pop();
        System.out.println(card + " has been drawn from deck");
        return card;
    }

    public List<Card> drawCards(int quantity) {
        return IntStream.range(0, quantity)
                .mapToObj(i -> drawCard())
                .collect(Collectors.toList());
    }

    public void fill(List<Card> newCards) {
        cards.addAll(newCards);
    }

    @Override
    public String toString() {
        return "Deck: " + cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(","));
    }
}
