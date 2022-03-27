package com.fouan.card;

public class MarmotteCard extends Card {

    private final int value;

    public MarmotteCard(Suit suit, Rank rank) {
        this(suit, rank, rank.value);
    }

    public MarmotteCard(Suit suit, Rank rank, int value) {
        super(suit, rank);
        this.value = value;
    }

    @Override
    public int compareTo(Card o) {
        if (o instanceof MarmotteCard) {
            return value - ((MarmotteCard) o).value;
        }
        return value - o.rank.value;
    }
}
