package com.ecnu.blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class deal with user's cards.
 * @author Jiayi Zhu
 * @date 2019-10-17 20:00
 */
public class Hand {
    private List<Card> cards;
    private boolean largerThan21;

    Hand () {
        this.cards = new ArrayList<>();
        this.largerThan21 = false;
    }

    public boolean isLargerThan21 () {
        return this.largerThan21;
    }

    /**
     * Get a list of the card in the hand.
     * @return A copy list of the cards.
     */
    public List<Card> getCards () {
        List<Card> cards = new ArrayList<>(this.cards);
        return cards;
    }



    /**
     * Add a card to the hand. Calculate whether the sum is larger than 21.
     * @param newCard The new card to be added
     */
    public void addCard (Card newCard) {
        this.cards.add(newCard);
        int sum = this.getSum();
        if (sum > 21) {
            this.largerThan21 = true;
        }
    }

    /**
     * Calculate the sum of cards.
     * @return The sum of cards
     */
    public int getSum () {
        int sum = 0;
        int ace11 = 0;

        for (Card card : this.cards) {
            // The real number on the card
            int no = card.getCardNumber() % 13;

            // 0 is Q
            if (no == 0) {
                no = 13;
            }

            // Convert J, Q, K to be 10
            if (no > 10) {
                no = 10;
            }

            // If it is ace, first consider it to be 11.
            if (no == 1) {
                no = 11;
                ace11++;
            }

            sum += no;
        }

        // If the sum is larger than 21, convert ace from 11 to 1
        while (sum > 21 && ace11 > 0) {
            sum -= 10;
            ace11--;
        }

        return sum;
    }

    /**
     * Get the number of cards.
     * @return The number of cards
     */
    public int getCardNumber () {
        return this.cards.size();
    }
}
