package com.fouan.player.strategy;

import com.fouan.card.*;

import java.util.List;
import java.util.Optional;

public class BotStrategy extends PlayerStrategy {
    private static final Card HALF_RANK_CARD = new Card(Suit.CLUB, Rank.SEVEN);
    private static final Card TOO_HIGH_RANK_CARD = new Card(Suit.CLUB, Rank.TEN);
    private static final int STOP_GAME_THRESHOLD_VALUES = 5;

    @Override
    public void executes(List<Card> knownCards, List<Card> unknownCards, Deck deck, Discard discard) {
        Card lastDiscarded = discard.getLast();
        Optional<Card> knownCardEqualsDiscarded = knownCards.stream()
                .filter(knownCard -> knownCard.compareTo(lastDiscarded) == 0)
                .findFirst();
        Optional<Card> maxKnownCardAboveDiscarded = knownCards.stream()
                .filter(card -> card.compareTo(lastDiscarded) > 0)
                .max(Card::compareTo);

        // try to take last discarded card
        if (knownCardEqualsDiscarded.isPresent()) {
            // discarded card is equal to one of known card, make the exchange
            exchangeKnownCardWithCard(knownCards, knownCardEqualsDiscarded.get(), discard.drawLast(), discard);
        } else if (lastDiscarded.compareTo(HALF_RANK_CARD) <= 0 && !unknownCards.isEmpty()) {
            // last discarded card's rank is below or equal 7 and there is still unknown cards, exchange first unknown card with discarded one
            exchangeUnknownCardWithCard(knownCards, unknownCards, discard.drawLast(), discard);
        } else if (lastDiscarded.compareTo(HALF_RANK_CARD) <= 0 && maxKnownCardAboveDiscarded.isPresent()) {
            // last discarded card's rank is below or equal 7 and all cards are known and discarded card is below one of them, exchange them
            exchangeKnownCardWithCard(knownCards, maxKnownCardAboveDiscarded.get(), discard.drawLast(), discard);
        } else {
            // draw card from deck
            Card drawnCard = deck.drawCard();
            Optional<Card> knownCardEqualsDrawn = knownCards.stream()
                    .filter(knownCard -> knownCard.compareTo(drawnCard) == 0)
                    .findFirst();
            Optional<Card> maxKnownCardAboveDrawn = knownCards.stream()
                    .max(Card::compareTo)
                    .filter(card -> card.compareTo(drawnCard) > 0);

            if (knownCardEqualsDrawn.isPresent()) {
                // drawn card is equal to one of known card, make the exchange
                exchangeKnownCardWithCard(knownCards, knownCardEqualsDrawn.get(), drawnCard, discard);
            } else if (drawnCard.compareTo(TOO_HIGH_RANK_CARD) < 0 && !unknownCards.isEmpty()) {
                // some cards are still unknown, replace drawn card by any unknown card
                exchangeUnknownCardWithCard(knownCards, unknownCards, drawnCard, discard);
            } else if (drawnCard.compareTo(TOO_HIGH_RANK_CARD) < 0 && maxKnownCardAboveDrawn.isPresent()) {
                // drawn card is below max known card, discard known and take drawn
                exchangeKnownCardWithCard(knownCards, maxKnownCardAboveDrawn.get(), drawnCard, discard);
            } else {
                // discard it when:
                // - card's rank is too high
                // - known card has same rank
                // - all cards are known, and they are all below drawn card
                discard.add(drawnCard);
            }
        }
    }

    @Override
    public boolean stopGame(List<Card> knownCards, List<Card> unknownCards, Deck deck, Discard discard) {
        int totalPoints = knownCards.stream()
                .mapToInt(Card::value)
                .sum();
        return unknownCards.isEmpty() && totalPoints <= STOP_GAME_THRESHOLD_VALUES;
    }
}
