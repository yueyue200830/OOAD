package com.ecnu.blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * A player play against dealer
 * @author Jiayi Zhu, Yiqing Tao
 * @date 2019-10-17 20:00
 */
public class Player {
    private Hand[] hands;
    private int handNumber;
    private int[] bet;

    Player(int handNumber) {
        this.handNumber = handNumber;
        this.hands = new Hand[this.handNumber];
        for (int i = 0; i < this.handNumber; i++) {
            this.hands[i] = new Hand();
        }
    }

    /**
     * Return the bet number of a specific hand.
     * @param handNo The number of the hand.
     * @return The bet of the hand.
     */
    public int getBet(int handNo) {
        return this.bet[handNo];
    }

    public void setBet(int[] bet) {
        this.bet = bet;
    }

    /**
     * Get the hand number.
     * @return The number of the hand.
     */
    public int getHandNumber() {
        return this.handNumber;
    }

    /**
     * Add a new card.
     * @param newCard The new card to be added to the hand
     * @param handNo The hand number.
     */
    public void receiveCard(Card newCard, int handNo) {
        this.hands[handNo].addCard(newCard);
    }

    /**
     * Get a list of the card in the hand.
     * @param handNo The hand number.
     * @return A copy list of the cards.
     */
    public List<Card> getCard(int handNo) {
        return this.hands[handNo].getCards();
    }

    /**
     * Get the sum of cards that is visible to dealer.
     * @return The sum of visible cards.
     */
    public List<Integer> tellCardSumToDealer() {
        List<Integer> sum = new ArrayList<>();
        for (int i = 0; i < this.handNumber; i++) {
            List<Card> cards = this.hands[i].getCards();
            int s = 0;
            for (Card card : cards) {
                if (card.isVisible()) {
                    s += card.getCardNumber();
                }
            }
            sum.add(s);
        }
        return sum;
    }

    /**
     * Calculate the sum of cards of a specific hand.
     * @param handNo The hand number.
     * @return The sum of cards
     */
    public int getCardSum(int handNo) {
        return this.hands[handNo].getSum();
    }

    /**
     * Get whether a specific hand is larger than 21 or not.
     * @param handNo The hand number.
     * @return True if the hand is over 21.
     */
    public boolean getLoseOrNot(int handNo) {
        return this.hands[handNo].isLargerThan21();
    }

    /**
     * Get the number of the card in a specific hand.
     * @param handNo The hand number.
     * @return The card number.
     */
    public int getCardNumber(int handNo) {
        return this.hands[handNo].getCardNumber();
    }

    /**
     * Double the hand i's bet
     * @param hand the number of hand
     */
    public void doubleBet(int hand) {
        this.bet[hand] *= 2;
    }
}
