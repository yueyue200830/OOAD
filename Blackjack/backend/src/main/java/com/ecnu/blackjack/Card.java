package com.ecnu.blackjack;

/**
 * This is card class which represents one card.
 * @author Jiayi Zhu, Yiqing Tao
 * @date 2019-10-17 20:00, 2019-10-18 22:02
 */
public class Card {
    /** The card number in range 1 - 52 */
    private int cardNumber;

    /** Whether the card is visible to dealer */
    private boolean visibility;


    /**
     * @param cardNumber The card number
     */
    Card(int cardNumber) {
        this.cardNumber = cardNumber;
        this.visibility = false;
    }

    public boolean isVisible() {
        return this.visibility;
    }

    public int getCardNumber() {
        return this.cardNumber;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
