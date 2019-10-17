package com.ecnu.blackjack;

import java.util.List;

/**
 * @author Jiayi Zhu
 * @date 2019-10-17
 */
public class Player {
    private Hand hand;
    private int bet;

    Player () {

    }

    public int getBet() {
        return this.bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public void receiveCard(Card newCard) {

    }

    public List<Card> getCard() {
        return this.hand.getCards();
    }

    public int getCardSum() {
        return this.hand.getSum();
    }
}
