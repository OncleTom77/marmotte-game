package com.fouan.game;

import com.fouan.player.Player;
import com.fouan.card.Deck;
import com.fouan.card.Discard;

import java.util.*;

public class Game {

    private List<Player> players;
    private Deck deck;
    private Discard discard;

    public Game(List<Player> players) {
        this.players = players;
    }

    public void run() {
        boolean stop = false;
        init();

        do {
            printGame();
            players.forEach(Player::plays);
        // game loop
            // each player plays once
            // player choose between 3 possibles actions:
            // - take the last discarded card and replace it with one of its card
            // - take the first card on the deck and replace it with one of its card
            // - take the first card on the deck and discard it

            // after a player finishes his turn, a card is discarded and if anyone has a card of the same rank, he can discarded too
            // only one player can do it
        } while (!stop);
    }

    private void init() {
        deck = Deck.marmotte();
        deck.shuffle();
        discard = new Discard();
        players.forEach(player -> {
            player.watch(discard);
            player.setDeckAndDrawInitialCards(deck);
        });
    }

    private void printGame() {
        System.out.println("Discard: " + discard.getLast());
    }
}
