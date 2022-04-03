package com.fouan.game;

import com.fouan.card.Deck;
import com.fouan.card.DeckWithDiscard;
import com.fouan.card.Discard;
import com.fouan.card.MarmotteCard;
import com.fouan.player.Player;

import java.util.List;

public class Game {

    private final List<Player> players;
    private DeckWithDiscard deckWithDiscard;

    public Game(List<Player> players) {
        this.players = players;
    }

    public void run() {
        boolean stop = false;
        Player playerStoppingGame = null;
        init();

        do {
            for (Player player : players) {
                if (player.equals(playerStoppingGame)) {
                    stop = true;
                    break;
                }

                System.out.println("-----------------------------");
                System.out.println("New turn");
                printGame();
                System.out.println(player);

                boolean shouldStopGameAtEndOfRound = player.plays();

                System.out.println("End of turn");
                System.out.println(player);

                if (playerStoppingGame == null && shouldStopGameAtEndOfRound) {
                    playerStoppingGame = player;
                }
            }
            // game loop
            // each player plays once
            // player choose between 3 possibles actions:
            // - take the last discarded card and replace it with one of its card
            // - take the first card on the deck and replace it with one of its card
            // - take the first card on the deck and discard it

            // after a player finishes his turn, a card is discarded and if anyone has a card of the same rank, he can discard it too
            // only one player can do it
        } while (!stop);

        //TODO: display each player's score
    }

    private void init() {
        deckWithDiscard = new DeckWithDiscard(Deck.marmotte());
        deckWithDiscard.shuffleAndInitDiscard();
        deckWithDiscard.dealCardsToPlayers(players);
    }

    private void printGame() {
        System.out.println("Discard: " + discard.getLast());
    }
}
