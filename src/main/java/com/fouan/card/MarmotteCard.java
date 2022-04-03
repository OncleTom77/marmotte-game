package com.fouan.card;

public class MarmotteCard extends Card {

    private final int value;

    public MarmotteCard(Suit suit, Rank rank) {
        super(suit, rank);
        if ((suit == Suit.DIAMOND || suit == Suit.HEART) && rank == Rank.KING) {
            this.value = 0;
        } else {
            this.value = rank.value;
        }
    }

    @Override
    public int value() {
        return value;
    }

    @Override
    public int compareTo(Card o) {
        if (o instanceof MarmotteCard) {
            return value - ((MarmotteCard) o).value;
        }
        return value - o.rank.value;
    }
}
