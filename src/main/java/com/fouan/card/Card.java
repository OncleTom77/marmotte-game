package com.fouan.card;

import java.util.Objects;

public class Card implements Comparable<Card> {

    protected final Suit suit;
    protected final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public boolean hasSameRank(Card other) {
        return rank == other.rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }

    @Override
    public String toString() {
        return rank.toString() + suit.toString();
    }

    @Override
    public int compareTo(Card o) {
        return this.rank.value - o.rank.value;
    }
}
