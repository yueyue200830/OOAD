package com.ecnu.blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiayi Zhu
 * @date 2019-10-17
 */
public class CardSet {
    private List<Card> cards;

    CardSet () {
        this.initCards();
        this.arrangeCard();
    }

    /**
     * Init 52 cards.
     */
    private void initCards() {
        int cardNumber = 52;
        this.cards = new ArrayList<Card>();
        for (int i = 0; i < cardNumber; i++) {
            this.cards.add(new Card(i));
        }
    }

    private void arrangeCard() {

    }

    private void removeCard() {
        this.cards.remove(0);
    }

    public Card drawCard() {
        Card firstCard = this.cards.get(0);
        this.removeCard();
        return firstCard;
    }
}
