package com.fouan.card;

public enum Suit {
    /** ♣ */
    CLUB("♣"),
    /** ♠ */
    SPADE("♠"),
    /** ♦ */
    DIAMOND("♦"),
    /** ♥ */
    HEART("♥");

    private final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
