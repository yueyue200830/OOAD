package com.ecnu.blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiayi Zhu
 * @date 2019-10-17
 */
public class Hand {
    private List<Card> cards;
    private boolean largerThan21;

    Hand () {
        this.cards = new ArrayList<Card>();
        this.largerThan21 = false;
    }

    public boolean isLargerThan21() {
        return this.largerThan21;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    /**
     *
     * @param newCard
     */
    public void addCard(Card newCard) {

    }

    public int getSum () {
        int sum = 0;
        return sum;
    }

    public int getCardNumber() {
        return this.cards.size();
    }
}
