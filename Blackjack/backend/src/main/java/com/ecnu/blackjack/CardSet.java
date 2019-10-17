package com.ecnu.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A deck of cards. The init number of cards is 52.
 * @author Jiayi Zhu
 * @date 2019-10-17 20:00
 */
public class CardSet {
    private List<Card> cards;

    /**
     * Initialize 52 cards and sort the sequence randomly.
     */
    CardSet () {
        int cardNumber = 52;

        // Initialize cards
        this.cards = new ArrayList<>();
        for (int i = 1; i <= cardNumber; i++) {
            this.cards.add(new Card(i));
        }

        // Sort cards' sequence randomly
        Collections.shuffle(this.cards);
    }

    /**
     * Remove the first card.
     */
    private void removeCard () {
        this.cards.remove(0);
    }

    /**
     * Get the first card and delete it from the set.
     * @return The first card in the set
     */
    public Card drawCard () {
        Card firstCard = this.cards.get(0);
        this.removeCard();
        return firstCard;
    }
}
