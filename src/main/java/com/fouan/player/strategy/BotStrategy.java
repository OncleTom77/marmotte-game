package com.fouan.player.strategy;

import com.fouan.card.*;

import java.util.List;
import java.util.Optional;

public class BotStrategy extends PlayerStrategy {
    private static final Card halfRankCard = new Card(Suit.CLUB, Rank.SEVEN);
    private static final Card tooHighRankCard = new Card(Suit.CLUB, Rank.TEN);

    @Override
    public void executes(List<Card> knownCards, List<Card> unknownCards, Deck deck, Discard discard) {
        Card lastDiscarded = discard.getLast();
        Optional<Card> knownCardEqualsDiscarded = knownCards.stream()
                .filter(knownCard -> knownCard.compareTo(lastDiscarded) == 0)
                .findFirst();
        Optional<Card> maxKnownCardAboveDiscarded = knownCards.stream()
                .filter(card -> card.compareTo(lastDiscarded) > 0)
                .max(Card::compareTo);

        // try take last discarded card
        if (knownCardEqualsDiscarded.isPresent()) {
            // discarded card is equal to one of known card, make the exchange
            exchangeWithDiscard(discard, knownCards, knownCardEqualsDiscarded.get());
        } else if (lastDiscarded.compareTo(halfRankCard) <= 0 && !unknownCards.isEmpty()) {
            // last discarded card's rank is below or equal 7 and there is still unknown cards, exchange first unknown card with discarded one
            exchangeWithDiscard(discard, unknownCards, unknownCards.get(0));
        } else if (lastDiscarded.compareTo(halfRankCard) <= 0 && maxKnownCardAboveDiscarded.isPresent()) {
            // last discarded card's rank is below or equal 7 and all cards are known and discarded card is below one of them, exchange them
            exchangeWithDiscard(discard, knownCards, maxKnownCardAboveDiscarded.get());
        } else {
            // draw card from deck
            Card drawnCard = deck.drawCard();
            Optional<Card> maxKnownCardAboveDrawn = knownCards.stream()
                    .max(Card::compareTo)
                    .filter(card -> card.compareTo(drawnCard) > 0);

            if (drawnCard.compareTo(tooHighRankCard) < 0 && !unknownCards.isEmpty()) {
                // some card are still unknown, replace drawn card by any unknown card
                Card firstUnknownCard = unknownCards.get(0);
                unknownCards.remove(firstUnknownCard);
                knownCards.add(drawnCard);
                discard.add(firstUnknownCard);
            } else if (drawnCard.compareTo(tooHighRankCard) < 0 && maxKnownCardAboveDrawn.isPresent()) {
                // drawn card is below max known card, discard known and take drawn
                Card maxKnownCard = maxKnownCardAboveDrawn.get();
                int maxKnownCardIndex = knownCards.indexOf(maxKnownCard);
                knownCards.set(maxKnownCardIndex, drawnCard);
                discard.add(maxKnownCard);
            } else {
                // discard it when:
                // - card's rank is too high
                // - known card has same rank
                // - all cards are known and they are all below drawn card
                discard.add(drawnCard);
            }
        }
    }
}
