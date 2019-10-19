package com.ecnu.blackjack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Run the project in console.
 * @author Yiqing Tao, Jiayi Zhu
 * @date 2019-10-18 19:00
 */
public class Console {

    /**
     * Static method for interpreting the card information
     * @param cardNumber the essential card number
     * @return the detailed info about the card
     */
    public static String getCardDescription(int cardNumber){
        String cardDescription = "";

        int cardValue = cardNumber % 13;

        //Pattern 1 for Club, 2 for Heart, 3 for Diamond, 4 for Spade
        int pattern = (cardNumber + 1) / 13;

        switch(pattern) {
            case 1:
                cardDescription = "Club ";
                break;
            case 2:
                cardDescription = "Heart ";
                break;
            case 3:
                cardDescription = "Diamond ";
                break;
            default:
                cardDescription = "Spade ";
                break;

        }
        if(cardValue == 1){
            cardDescription += "Ace";
        }
        else if(cardValue == 11){
            cardDescription += "J";
        }
        else if(cardValue == 12){
            cardDescription += "Q";
        }
        else if(cardValue == 0){
            cardDescription += "K";
        }
        else{
            cardDescription += cardValue;
        }
        return cardDescription;
    }

    /**
     * After every turn, print the overall information of the game on console.
     * @param decker the decker in the game
     */
    public static void presentRound(Decker decker, boolean hasFinished){
        int[][] currInfo = decker.getRoundInfo(hasFinished);

        System.out.println("-------------------------------------------");
        if (hasFinished) {
            System.out.println("Final Information:");
        } else {
            System.out.println("Round Information: ");
        }

        for(int i = 0; i < currInfo.length - 1;i++){
            System.out.println("Player " + (i + 1) + ": ");
            for(int j = 0; j < currInfo[i].length;j++){
                if(currInfo[i][j] != -1) {
                    System.out.println(getCardDescription(currInfo[i][j]));
                }
            }
        }

        System.out.println("Dealer's Infomation: ");
        int indexOfDealer = currInfo.length - 1;
        for(int j = 0; j <  currInfo[indexOfDealer].length; j++){
            if(currInfo[indexOfDealer][j] != -1) {
                System.out.println(getCardDescription(currInfo[indexOfDealer][j]));
            }
        }
    }

    /**
     * After the game end, print out all winners
     * @param winnerList the list of winners
     */
    public static void printWinner(Decker decker, List<Integer> winnerList){
        if (winnerList.size() == 0) {
            System.out.println("Unfortunately the winner is dealer! All players lose their bet! ");
        } else {
            for (int i : winnerList) {
                System.out.println("Player " + i + "! Congratulation to win your bet for " + decker.getBet(i) + " dollars.");
            }
        }
    }

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String hint;
        final int upMoreDrawTimes = 3;

        try{
            //First to get the number of the players, then initialize the whole game by creating decker.
            hint = "Welcome to blackjack, please enter the number of players.";
            System.out.println(hint);
            int numberOfPlayers = Integer.parseInt(in.readLine());
            Decker decker = new Decker(numberOfPlayers);

            //Now, it's time to ask all the players to set their bet.
            for(int i = 0; i< numberOfPlayers; i++){
                hint = "For player " + (i + 1) + ", now input the bet";
                System.out.println(hint);
                int currentBet = Integer.parseInt(in.readLine());
                decker.setBet(currentBet,i);
            }

            //After the bet decision, first draw two cards for every player including dealer.
            //Pay attention to the wrong input players may give.
            decker.initialDraw();

            for(int i = 0; i < numberOfPlayers; i++) {
                int drawCount = 0;
                hint = "Player " + (i + 1) + ": Do you want to continue to draw? (Y for continue and N for not)";
                presentRound(decker, false);

                while (drawCount < upMoreDrawTimes) {
                    System.out.println(hint);
                    String result = in.readLine();
                    if (result.equals("Y") || result.equals("y")) {
                        int drawNumber = decker.basicDraw(i,false);
                        System.out.println("You get a " + getCardDescription(drawNumber));
                        drawCount++;

                        if(!decker.getPlayerLose(i)) {
                            hint = "Player " + (i + 1) + ": Do you want to continue to draw? (Y for continue and N for not)";
                        }
                        else{
                            System.out.println("Oops, seems you are over 21. Lose game! ");
                            break;
                        }
                    }
                    else if(result.equals("N") || result.equals("n")){
                        System.out.println("Player " + (i + 1) + ": stop drawing. ");
                        break;
                    }
                    else{
                        hint = "Wrong input, input again!";
                    }
                }
            }

            //If all players die, then the game end in advance.
            if(decker.endAdvance()){
                System.out.println("All player loses, the game end! Winner is dealer!");
                return;
            }

            // After all the players finished drawing, it's time for dealer to draw.
            // Before draw any cards, dealer hae the right to find out all the visible results of his opponents.
            System.out.println("Now it's dealer's turn to draw.");
            int drawCount = 0;
            while(drawCount < upMoreDrawTimes){
                decker.getPlayerSum();
                if(decker.askDealer()){
                    int cardNumber = decker.basicDraw(-1,false);
                    System.out.println("Dealer get a " + getCardDescription(cardNumber));
                    drawCount ++;

                    //If dealer lose in the progress of drawing card, players win.
                    if(decker.getDealerLose()){
                        break;
                    }
                }
                else{
                    break;
                }
            }

            // Now the whole progress of game is finished. It is time to calculate the winner and deal with the bet.
            presentRound(decker, true);
            List<Integer> winnerList = decker.judgeWin();
            System.out.println("Now declare the winner of the game:");
            printWinner(decker, winnerList);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
