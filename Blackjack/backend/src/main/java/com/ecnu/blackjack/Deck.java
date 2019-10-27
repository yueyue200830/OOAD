package com.ecnu.blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * Deck class serves as controller to connect dealer and player.
 * @author Yiqing Tao, Jiayi Zhu
 * @date 2019-10-18 16:20
 */
public class Deck {
    private CardSet currentSet;
    private Player[] playerGroup;
    private int playerNumber;
    private Dealer dealer;

    /**
     * Constructor of Deck, initializing the game.
     * @param playerNumber Create one dealer, card set and playerNumber players.
     * @param playerHands the list of hand number.
     */
    Deck(int playerNumber, int[] playerHands) {
        this.playerNumber = playerNumber;
        this.currentSet = new CardSet();
        this.playerGroup = new Player[this.playerNumber];
        this.dealer = new Dealer();
        for (int i = 0 ; i < this.playerNumber; i++) {
            playerGroup[i] = new Player(playerHands[i]);
        }
    }

    /**
     * After get the number of player, asking players their bet.
     * @param bet the amount of money the player want to get involved
     * @param playerNo current number of player
     */
    public void setBet(int[] bet, int playerNo) {
        this.playerGroup[playerNo].setBet(bet);
    }

    /**
     * In the beginning of the game, draw every player including the dealer two cards.
     * Notice: Every first card of the player should be invisible to the dealer.
     */
    public void initialDraw() {
        for (int i = 0; i < this.playerNumber; i++) {
            for (int j = 0; j < this.playerGroup[i].getHandNumber(); j++) {
                this.basicDraw(i, j, true);
                this.basicDraw(i, j, false);
            }
        }
        this.basicDraw(-1, 0,true);
        this.basicDraw(-1, 0,false);
    }

    /**
     * The basic operation of drawing a card and give it to the player or dealer.
     * Notice: The card is visible if it is owned by dealer or it is not the first card of players.
     * @param playerNo current number of player.
     * @param handNo the number of hand.
     * @param firstTime whether it is the first card of the player.
     * @return the number of the card that player got.
     */
    public int basicDraw(int playerNo, int handNo, boolean firstTime) {
        Card newCard = this.currentSet.drawCard();
        if (playerNo != -1 && firstTime) {
            newCard.setVisibility(false);
            this.playerGroup[playerNo].receiveCard(newCard, handNo);
        } else {
            newCard.setVisibility(true);
            if (playerNo == -1) {
                this.dealer.receiveCard(newCard);
            } else {
                this.playerGroup[playerNo].receiveCard(newCard, handNo);
            }
        }
        return newCard.getCardNumber();
    }

    /**
     * Ask dealer whether it wants another card after all players finished their turns.
     * @return a boolean value, indicating whether to draw another card.
     */
    public boolean askDealer() {
        return this.dealer.drawOrNot();
    }

    /**
     * Before asking the dealer whether it wants to draw, first get all the cards sun of all players,
     * in order to inform this information to the dealer, helping him to analyze the situation and decide
     * whether to draw or not.
     * Then, ask dealer to draw.
     */
    public List<List<Integer>> askDealerToDraw() {
        // Tell hand's sum to dealer.
        List<List<Integer>> sum = new ArrayList<>();
        List<List<Integer>> currCardValue = new ArrayList<>();
        for (Player player : this.playerGroup) {
            List<Integer> s = player.getVisibleCardSum();
            sum.add(s);
        }
        this.dealer.receiveSum(sum);

        // Draw actions.
        int drawCount = 0;
        final int upMoreDrawTimes = 3;
        List<Integer> cardInfo = new ArrayList<>();
        List<Integer> loseInfo = new ArrayList<>();
        while (drawCount < upMoreDrawTimes) {
            if (this.askDealer()) {
                int value = this.basicDraw(-1, 0, false);
                cardInfo.add(value);
                drawCount ++;

                //If dealer lose in the progress of drawing card, players win.
                if (this.getDealerLose()) {
                    loseInfo.add(1);
                    break;
                }
            } else {
                loseInfo.add(0);
                break;
            }
        }
        currCardValue.add(cardInfo);
        currCardValue.add(loseInfo);
        return currCardValue;
    }

    /**
     * During or after the progress of player drawing cards, the loss situation of players should be recorded.
     * @param playerNumber the number of player
     * @param handNo the hand number of the player
     * @return true if the player loose.
     */
    public boolean getPlayerLose(int playerNumber, int handNo) {
        return this.playerGroup[playerNumber].getLoseOrNot(handNo);
    }

    /**
     * Show current game information to frontend when every turn is over.
     * @param hasFinished whether the game is finished.
     * @return the specific info about every hand.
     */
    public List<List<List<Integer>>> getRoundInfo(boolean hasFinished) {
        List<List<List<Integer>>> overallInfo = new ArrayList<>();

        // Get player's information
        for (int i = 0; i < this.playerNumber; i++) {
            List<List<Integer>> playerInfo = new ArrayList<>();
            for (int j = 0; j < this.playerGroup[i].getHandNumber(); j++) {
                List<Integer> handInfo = new ArrayList<>();
                List<Card> cardList = this.playerGroup[i].getCard(j);
                for (Card card: cardList) {
                    handInfo.add(card.getCardNumber());
                }
                playerInfo.add(handInfo);
            }
            overallInfo.add(playerInfo);
        }

        // Get dealer's information
        List<List<Integer>> dealerInfo = new ArrayList<>();
        List<Integer> dealerHand = new ArrayList<>();
        List<Card> dealerCard = this.dealer.getCard();
        int firstDealerCard = hasFinished ? 0 : 1;
        for (int i = firstDealerCard; i < dealerCard.size(); i++) {
            dealerHand.add(dealerCard.get(i).getCardNumber());
        }
        dealerInfo.add(dealerHand);
        overallInfo.add(dealerInfo);

        return overallInfo;
    }

    public boolean getDealerLose() {
        return this.dealer.getLoseOrNot();
    }

    /**
     * Before dealer continue to draw, if all players lose then dealer wins. The game end in advance.
     * @return whether the game has ended.
     */
    public boolean endAdvance() {
        for (Player player : this.playerGroup) {
            for (int i = 0; i < player.getHandNumber(); i++) {
                if (!player.getLoseOrNot(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * The final step of the game, judge who are the winners.
     * @return list of wining hands.
     */
    public List<List<Integer>> judgeWin() {
        List<List<Integer>> winnerList = new ArrayList<>();

        int dealerSum = this.dealer.getCardSum();
        int dealerCardNumber = this.dealer.getCardNumber();
        boolean dealerLose = this.dealer.getLoseOrNot();

        for (int i = 0; i < this.playerNumber; i++) {
            Player player = this.playerGroup[i];
            List<Integer> winner = new ArrayList<>();
            for (int j = 0; j < player.getHandNumber(); j++) {
                if (player.getLoseOrNot(j)) {
                    continue;
                }

                int playerCardNumber = player.getCardNumber(j);
                int cardSum = player.getCardSum(j);
                boolean isLargerThanDealer = cardSum > dealerSum;
                boolean equalSumAndFewerCards = cardSum == dealerSum && playerCardNumber <= dealerCardNumber;
                if (dealerLose || isLargerThanDealer || equalSumAndFewerCards) {
                    winner.add(j);
                }
            }
            winnerList.add(winner);
        }

        return winnerList;
    }

    /**
     * Get the bet of a specific hand of a specific player.
     * @param playerNo the number of player to return his / her bet.
     * @param handNo the number of the hand of the player.
     * @return the bet of the hand.
     */
    public int getBet(int playerNo, int handNo){
        return this.playerGroup[playerNo].getBet(handNo);
    }

    /**
     * Get the number of hand of a specific player.
     * @param playerNo the number of player
     * @return the number of hand of the player.
     */
    public int getPlayerHandNumber(int playerNo) {
        return this.playerGroup[playerNo].getHandNumber();
    }

    /**
     * Double player i's bet.
     * @param playerNo the number of player
     */
    public void doubleBet(int playerNo, int hand) {
        this.playerGroup[playerNo].doubleBet(hand);
    }

}
