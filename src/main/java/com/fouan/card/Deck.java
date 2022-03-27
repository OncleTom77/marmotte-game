package com.fouan.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck standard() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }

        return new Deck(cards);
    }

    public static Deck marmotte() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                if ((suit == Suit.DIAMOND || suit == Suit.HEART) && rank == Rank.KING) {
                    cards.add(new MarmotteCard(suit, rank, 0));
                }
                cards.add(new MarmotteCard(suit, rank));
            }
        }

        return new Deck(cards);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.remove(0);
    }

    public List<Card> drawCards(int quantity) {
        return IntStream.range(0, quantity)
                .mapToObj(i -> cards.remove(0))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Deck: " + cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(","));
    }
}
