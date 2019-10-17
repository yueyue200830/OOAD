package com.ecnu.blackjack;

import java.util.List;

/**
 * A player play against dealer
 * @author Jiayi Zhu
 * @date 2019-10-17 20:00
 */
public class Player {
    private Hand hand;
    private int bet;

    Player () {
        this.hand = new Hand();
    }

    public int getBet () {
        return this.bet;
    }

    public void setBet (int bet) {
        this.bet = bet;
    }

    /**
     * Add a new card
     * @param newCard The new card to be added to the hand
     */
    public void receiveCard (Card newCard) {
        this.hand.addCard(newCard);
    }
    /**
     * Get a list of the card in the hand.
     * @return A copy list of the cards.
     */
    public List<Card> getCard () {
        return this.hand.getCards();
    }

    /**
     * Calculate the sum of cards.
     * @return The sum of cards
     */
    public int getCardSum () {
        return this.hand.getSum();
    }
}
