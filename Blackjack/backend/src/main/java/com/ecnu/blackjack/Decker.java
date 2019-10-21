package com.ecnu.blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Decker class serves as controller to connect dealer and player.
 * @author Yiqing Tao, Jiayi Zhu
 * @date 2019-10-18 16:20
 */
public class Decker {
    private CardSet currentSet;
    private Player[] playerGroup;
    private int[] playerSum;
    private boolean[] loseOrNot;
    private int playerNumber;
    private Dealer dealer;
    private final int maxCardNumber = 5;

    /**
     * Constructor of Decker, initializing the game.
     * @param playerNumber Create one dealer, card set and playerNumber players.
     */
    public Decker(int playerNumber){
        this.playerNumber = playerNumber;
        this.currentSet = new CardSet();
        this.playerGroup = new Player[this.playerNumber];
        this.loseOrNot = new boolean[this.playerNumber];
        this.dealer = new Dealer();
        this.playerSum = new int[this.playerNumber];
        for(int i = 0 ;i < this.playerNumber; i++){
            playerGroup[i] = new Player();
            loseOrNot[i] = false;
        }
    }

    /**
     * After get the number of player, asking players their bet.
     * @param bet the amount of money the player want to get involved
     * @param playerNo current number of player
     */
    public void setBet(int bet,int playerNo){
        this.playerGroup[playerNo].setBet(bet);
    }

    /**
     * In the beginning of the game, draw every player including the dealer two cards.
     * Notice: Every first card of the player should be invisible to the dealer.
     */
    public void initialDraw(){
        for(int i = 0; i < this.playerNumber; i++){
            this.basicDraw(i,true);
            this.basicDraw(i,false);
        }
        this.basicDraw(-1,true);
        this.basicDraw(-1,false);
    }

    /**
     * The basic operation of drawing a card and give it to the player or dealer.
     * Notice: The card is visible if it is owned by dealer or it is not the first card of players.
     * @param playerNo current number of player
     * @param firstTime whether it is the first card of the player.
     * @return the number of the card that player got.
     */
    public int basicDraw(int playerNo, boolean firstTime){
        Card newCard = this.currentSet.drawCard();
        if(playerNo != -1 && firstTime){
            newCard.setVisibility(false);
            this.playerGroup[playerNo].receiveCard(newCard);
        }
        else {
            newCard.setVisibility(true);
            if(playerNo == -1) {
                this.dealer.receiveCard(newCard);
            }
            else{
                this.playerGroup[playerNo].receiveCard(newCard);
            }
        }
        return newCard.getCardNumber();
    }

    /**
     * Ask dealer whether it wants another card after all players finished their turns.
     * @return a boolean value, indicating whether to draw another card.
     */
    public boolean askDealer(){
        int[] sum = new int[this.playerNumber];
        for (int i = 0; i < this.playerNumber; i++) {
            sum[i] = this.playerGroup[i].tellCardSumToDealer();
        }
        this.dealer.receiveSum(sum);
        return this.dealer.drawOrNot();
    }

    /**
     * Before asking the dealer whether it wants to draw, first get all the cards sun of all players,
     * in order to inform this information to the dealer, helping him to analyze the situation and decide
     * whether to draw or not.
     */
    public void getPlayerSum(){
        for(int i = 0; i < this.playerNumber; i++){
            this.playerSum[i] = this.playerGroup[i].getCardSum();
        }
    }

    /**
     * During or after the progress of player drawing cards, the loss situation of players should be recorded.
     */
    public boolean getPlayerLose(int playerNumber){
        this.loseOrNot[playerNumber] = this.playerGroup[playerNumber].getLoseOrNot();
        return this.loseOrNot[playerNumber];
    }

    /**
     * Show current game information to frontend when every turn is over.
     * @return the specific info about every hand.
     */
    public int[][] getRoundInfo(boolean hasFinished){
        int[][] overallInfo = new int[this.playerNumber + 1][maxCardNumber];
        for(int i = 0;i <= this.playerNumber; i++){
            for(int j = 0; j < maxCardNumber; j++){
                overallInfo[i][j] = -1;
            }
        }

        int countCard;
        for(int i = 0; i < this.playerNumber; i++){
            List<Card> cardList = this.playerGroup[i].getCard();
            countCard = 0;
            for(Card currentCard:cardList){
                overallInfo[i][countCard++] = currentCard.getCardNumber();
            }
        }

        List<Card> dealerCard = this.dealer.getCard();
        countCard = 0;
        int firstDealerCard = hasFinished ? 0 : 1;
        for(int i = firstDealerCard;i< dealerCard.size();i++){
            overallInfo[this.playerNumber][countCard++] = dealerCard.get(i).getCardNumber();
        }
        return overallInfo;
    }

    public boolean getDealerLose(){
        return this.dealer.getLoseOrNot();
    }

    /**
     * Before dealer continue to draw, if all players lose then dealer wins. The game end in advance.
     * @return whether the game has ended.
     */
    public boolean endAdvance(){
        boolean flag = true;
        for(int i = 0;i < this.playerNumber; i++){
            if(!this.loseOrNot[i]){
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * The final step of the game, judge who are the winners.
     * @return Int array. The winner list.
     */
    public List<Integer> judgeWin() {
        List<Integer> winnerList = new ArrayList<>();

        int dealerSum = this.dealer.getCardSum();
        int dealerCardNumber = this.dealer.getCardNumber();
        boolean dealerLose = this.dealer.getLoseOrNot();

        for (int i = 0; i < this.playerNumber; i++) {
            if (this.loseOrNot[i]) {
                continue;
            }

            int playerCardNumber = this.playerGroup[i].getCardNumber();
            boolean isLargerThanDealer = this.playerSum[i] > dealerSum;
            boolean EqualSumAndFewerCards = this.playerSum[i] == dealerSum && playerCardNumber <= dealerCardNumber;
            if (dealerLose || isLargerThanDealer || EqualSumAndFewerCards){
                winnerList.add(i);
            }
        }

        return winnerList;
    }

    /**
     * Get the bet of a specific player.
     * @param playerNo the number of player to return his / her bet
     * @return the bet of a specific player.
     */
    public int getBet(int playerNo){
        return this.playerGroup[playerNo].getBet();
    }

}
