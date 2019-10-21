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
    private static int numberOfPlayers = 0;
    private static Decker decker;
    private static final int UP_MORE_DRAW_TIMES = 3;

    /**
     * Static method for interpreting the card information.
     * @param cardNumber the essential card number.
     * @return the detailed info about the card.
     */
    public static String getCardDescription(int cardNumber) {
        String cardDescription;

        int cardValue = cardNumber % 13;

        //Pattern 1 for Club, 2 for Heart, 3 for Diamond, 4 for Spade
        int pattern = (cardNumber + 1) / 13;

        switch (pattern) {
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

        if (cardValue == 1) {
            cardDescription += "Ace";
        } else if (cardValue == 11) {
            cardDescription += "J";
        } else if (cardValue == 12) {
            cardDescription += "Q";
        } else if (cardValue == 0) {
            cardDescription += "K";
        } else {
            cardDescription += cardValue;
        }
        return cardDescription;
    }

    /**
     * After every turn, print the overall information of the game on console.
     * @param decker the decker in the game.
     */
    public static void presentRound(Decker decker, boolean hasFinished) {
        int[][] currInfo = decker.getRoundInfo(hasFinished);

        System.out.println("-------------------------------------------");
        if (hasFinished) {
            System.out.println("Final Information:");
        } else {
            System.out.println("Round Information: ");
        }

        for (int i = 0; i < currInfo.length - 1; i++) {
            System.out.println("Player " + (i + 1) + ": ");
            for (int j = 0; j < currInfo[i].length;j++) {
                if (currInfo[i][j] != -1) {
                    System.out.println(getCardDescription(currInfo[i][j]));
                }
            }
        }

        System.out.println("Dealer's Information: ");
        int indexOfDealer = currInfo.length - 1;
        for (int j = 0; j <  currInfo[indexOfDealer].length; j++) {
            if (currInfo[indexOfDealer][j] != -1) {
                System.out.println(getCardDescription(currInfo[indexOfDealer][j]));
            }
        }
    }

    /**
     * After the game end, print out all winners.
     * @param winnerList the list of winners.
     */
    public static void printWinner(Decker decker, List<Integer> winnerList) {
        if (winnerList.size() == 0) {
            System.out.println("Unfortunately the winner is dealer! All players lose their bet! ");
        } else {
            for (int i : winnerList) {
                System.out.println("Player " + (i + 1) + "! Congratulation to win your bet for " + decker.getBet(i) + " dollars.");
            }
        }
    }

    /**
     * Basic initialization of game, get number of players from IO and set decker already.
     * @throws IOException throws the potential IOException.
     */
    public static void startGame() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String hint = "Welcome to blackjack, please enter the number of players";
        System.out.println(hint);
        numberOfPlayers = Integer.valueOf(in.readLine());
        decker = new Decker(numberOfPlayers);
    }

    /**
     * Ask all players their bet.
     * @throws IOException throws possible IOException caused by io.
     */
    public static void askBet() throws IOException {
        String hint;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i< numberOfPlayers; i++){
            hint = "For player " + i + ", now input the bet";
            System.out.println(hint);
            int currentBet = Integer.valueOf(in.readLine());
            decker.setBet(currentBet,i);
        }
    }

    /**
     * First round of the game, every player including dealer draw two cards, one visible and the other invisible.
     */
    public static void firstRound() {
        decker.initialDraw();
    }

    /**
     * Now it is players' round to decide whether to draw or not.
     * @throws IOException throws possible IOException caused by io.
     */
    public static void playerRound() throws IOException {
        String hint;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < numberOfPlayers; i++) {
            int drawCount = 0;
            hint = "Player " + (i + 1) + ": Do you want to continue to draw? (Y for continue and N for not)";
            presentRound(decker, false);

            while (drawCount < UP_MORE_DRAW_TIMES) {
                System.out.println(hint);
                String result = in.readLine();
                if (result.equals("Y") || result.equals("y")) {
                    int drawNumber = decker.basicDraw(i,false);
                    System.out.println("You get a " + getCardDescription(drawNumber));
                    drawCount++;

                    if (!decker.getPlayerLose(i)) {
                        hint = "Player " + (i + 1) + ": Do you want to continue to draw? (Y for continue and N for not)";
                    } else{
                        System.out.println("Oops, seems you are over 21. Lose game! ");
                        break;
                    }
                } else if (result.equals("N") || result.equals("n")) {
                    System.out.println("Player " + (i + 1) + ": stop drawing. ");
                    break;
                } else {
                    hint = "Wrong input, input again!";
                }
            }
        }
        in.close();
    }

    /**
     * After all players done, check the game status of all players, if all of them lose,
     * dealer wins without having to draw.
     * @return whether the game has to continue.
     */
    public static boolean checkPlayerLose() {
        return decker.endAdvance();
    }

    /**
     * Dealer's turn to draw card, according to previous players' condition.
     */
    public static void dealerTurn() {
        System.out.println("Now it's dealer's turn to draw.");
        decker.askDealerToDraw();
    }

    /**
     * After dealer finished, check the final winners.
     */
    public static void checkFinalState() {
        presentRound(decker, true);
        List<Integer> winnerList = decker.judgeWin();
        System.out.println("Now declare the winner of the game:");
        printWinner(decker, winnerList);
    }

    public static void main(String[] args) {
        try{
            //First to get the number of the players, then initialize the whole game by creating decker.
            startGame();

            //Now, it's time to ask all the players to set their bet.
           askBet();

            //After the bet decision, first draw two cards for every player including dealer.
            //Pay attention to the wrong input players may give.
            firstRound();

            //Now start playerRound, ask player if they want to continuously draw cards
            playerRound();


            //If all players die, then the game end in advance.
            if (checkPlayerLose()) {
                System.out.println("All player loses, the game end! Winner is dealer!");
                return;
            }

            // After all the players finished drawing, it's time for dealer to draw.
            // Before draw any cards, dealer hae the right to find out all the visible results of his opponents.
           dealerTurn();

            // Now the whole progress of game is finished. It is time to calculate the winner and deal with the bet.
            checkFinalState();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
