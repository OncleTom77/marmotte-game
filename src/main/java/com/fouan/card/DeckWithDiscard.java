package com.fouan.card;

import com.fouan.game.DiscardObserver;
import com.fouan.player.Player;

import java.util.List;

public class DeckWithDiscard {
    private final Deck deck;
    private final Discard discard;

    public DeckWithDiscard(Deck deck) {
        this.deck = deck;
        discard = new Discard();
    }

    public void shuffleAndInitDiscard() {
        deck.shuffle();
        discard.add(deck.drawCard()); // init discard
    }

    public void dealCardsToPlayers(List<Player> players, int nbCards) {
        players.forEach(player -> {
            List<Card> cards = deck.drawCards(nbCards);
            player.initPlayer(this, cards);
        });
    }

    public Card drawCard() throws EmptyDeckException {
        try {
            return deck.drawCard();
        } catch (EmptyDeckException e) {
            fillDeckFromDiscard();
            return deck.drawCard();
        }
    }

    public Card getLastDiscarded() {
        return discard.getLast();
    }

    public Card drawLastDiscarded() {
        return discard.drawLast();
    }

    public void discard(Card card) {
        discard.add(card);
    }

    public void addDiscardObserver(DiscardObserver observer) {
        discard.addObserver(observer);
    }

    private void fillDeckFromDiscard() {
        List<Card> newCards = this.discard.empty(true);
        deck.fill(newCards);
        deck.shuffle();
    }
}
