package com.ecnu.blackjack;

/**
 * @author Jiayi Zhu
 * @date 2019-10-17
 */
public class Card {
    private int cardNumber;
    private boolean visibility;

    Card (int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean isVisibility() {
        return this.visibility;
    }

    public int getCardNumber() {
        return this.cardNumber;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
