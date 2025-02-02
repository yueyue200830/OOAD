package com.ecnu.blackjack;

import java.util.List;

/**
 * Dealer is the player managed by computer
 * @author Jiayi Zhu
 * @date 2019-10-17 20:00
 */
public class Dealer {
    private Hand hand;
    private List<List<Integer>> playerSum;

    Dealer() {
        this.hand = new Hand();
    }

    /**
     * Set the list of players' sum
     * @param playerSum The list of player with the list of its hands.
     */
    public void receiveSum(List<List<Integer>> playerSum) {
        this.playerSum = playerSum;
    }

    /**
     * Decide whether to draw or not
     * @return whether to draw
     */
    public boolean drawOrNot() {
        return this.hand.getCardNumber() < 5 && this.hand.getSum() <= 17;
    }

    /**
     * Add a new card
     * @param newCard The new card to be added to the hand
     */
    public void receiveCard(Card newCard) {
        this.hand.addCard(newCard);
    }

    /**
     * Get a list of the card in the hand.
     * @return A copy list of the cards.
     */
    public List<Card> getCard() {
        return this.hand.getCards();
    }

    /**
     * Calculate the sum of cards.
     * @return The sum of cards
     */
    public int getCardSum() {
        return this.hand.getSum();
    }

    public boolean getLoseOrNot() {
        return this.hand.isLargerThan21();
    }

    public int getCardNumber() {
        return this.hand.getCardNumber();
    }
}
