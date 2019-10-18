package com.ecnu.blackjack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Run the project in console.
 * @author Yiqing Tao
 * @date 2019-10-18 19:00
 */
public class Console {

    /**
     * static method for interpreting the card information
     * @param cardNumber the essential card number
     * @return the detailed info about the card
     */
    public static String getCardDescrption(int cardNumber){
        String cardDescription = "";

        int cardValue = cardNumber % 13;

        //Pattern 1 for Club, 2 for Heart, 3 for Diamond, 4 for Spade
        int pattern = cardNumber / 13;

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
        else if(cardValue == 13){
            cardDescription += "K";
        }
        else{
            cardDescription += cardValue;
        }
        return cardDescription;
    }

    /**
     * after every turn, print the overall information of the game on console.
     * @param decker
     */
    public static void presentRound(Decker decker){
        int[][] currInfo = decker.getRoundInfo();
        System.out.println("Round Information: ");
        for(int i = 0; i < currInfo.length - 1;i++){
            System.out.println("Player " + i + ": ");
            for(int j = 0; j < currInfo[i].length;j++){
                System.out.println(getCardDescrption(currInfo[i][j]));
            }
        }
        System.out.println("Dealer's Infomation: ");
        int indexOfDealer = currInfo.length - 1;
        for(int j = 0; j <  currInfo[indexOfDealer].length; j++){
            System.out.println(getCardDescrption(currInfo[indexOfDealer][j]));
        }
    }

    /**
     * After the game end, print out all winners
     * @param winnerList the list of winners
     */
    public static  void printWinner(int[] winnerList){
        for(int i = 0; i < winnerList.length; i++){
            System.out.println("Player " + i + "wins!");
        }
    }


    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String hint;
        final int upMoreDrawTimes = 3;
        int numberOfPlayers = 0;
        try{

            //First to get the number of the players, then initialize the whole game by creating decker.

            hint = "welcome to blackjack, please enter the number of players";
            System.out.println(hint);
            numberOfPlayers = Integer.valueOf(in.readLine());
            Decker decker = new Decker(numberOfPlayers);

            //Now, it's time to ask all the players to set their bet.

            for(int i = 0; i< numberOfPlayers; i++){
                hint = "For player "+ i + ", now input the bet";
                System.out.println(hint);
                int currentBet = Integer.valueOf(in.readLine());
                decker.setBet(currentBet,i);
            }

            //After the bet decision, first draw two cards for every player including dealer.
            //Pay attention to the wrong input players may give.

            decker.initialDraw();
            presentRound(decker);

            int drawCount = 0;
            for(int i = 0; i < numberOfPlayers; i++) {
                while (drawCount < upMoreDrawTimes) {
                    hint = "Player " + i + ": Whether you want to continue to draw?(Y for continue and N for not)";
                    System.out.println(hint);
                    String result = in.readLine();
                    if (result.equals("Y")) {
                        int drawNumber = decker.basicDraw(i,false);
                        System.out.println("You get a " + getCardDescrption(drawNumber));
                        if(!decker.getPlayerLose(i)) {
                            drawCount++;
                        }
                        else{
                            System.out.println("Oops, seems you are over 21. Lose game! ");
                            break;
                        }
                    }
                    else if(result.equals("N")){
                        System.out.println("Player " + i + ": stop drawing. ");
                        break;
                    }
                    else{
                        hint = "Wrong input, input again!";
                        continue;
                    }
                }
                presentRound(decker);
            }

            //If all players die, then the game end in advance.
            if(decker.endAdvance()){
                System.out.println("All player loses, the game end! Winner is dealer! ");
                return;
            }

            // After all the players finished drawing, it's time for dealer to draw
            // Before draw any cards, dealer hae the right to find out all the visible results of his opponents
            System.out.println("Now it's dealer's turn to draw");
            drawCount = 0;
            int[] winnerList;
            while(drawCount < upMoreDrawTimes){
                decker.getPlayerSum();
                if(decker.askDealer()){
                    int cardNumber = decker.basicDraw(-1,false);
                    System.out.println("Dealer get a " + getCardDescrption(cardNumber));
                    drawCount ++;

                    //If dealer lose in the progress of drawing card, players win.
                    if(decker.getDealerLose()){
                        System.out.println("Oops, Dealer lose, all players win!");
                        winnerList = decker.judgeWin();
                        printWinner(winnerList);
                        return;
                    }
                }
                else{
                    break;
                }
            }

            // Now the whole progress of game is finished. It is time to calculate the winner and deal with the bet.

            winnerList = decker.judgeWin();
            System.out.println("Now declare the winner of the game:");
            if(winnerList[0] != -1) {
                for (int i = 0; i < winnerList.length; i++) {
                    System.out.println("Player" + winnerList[i] + "! congratulation to win your bet for " + decker.getBet(i) + "dollars.");
                }
            }
            else{
                System.out.println("Unfortunately the winner is dealer! All players lose their bet! ");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }





    }

}
