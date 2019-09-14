package com.ecnu;

public class GameControl {
    private int numberAnt;
    private double[] velocityGroup;
    private double timeInterval;
    private double[] position;
    private double stickLength;
    private double minTime;
    private double maxTime;

    GameControl(int numberAnt, double[] velocityGroup, double timeInterval, double[] position, double stickLength) {
        this.numberAnt = numberAnt;
        this.velocityGroup = velocityGroup;
        this.timeInterval = timeInterval;
        this.position = position;
        this.stickLength = stickLength;
        this.minTime = Double.POSITIVE_INFINITY;
        this.maxTime = 0;
    }

    public void enumerateGame() {
        int allTimes = (int) (Math.pow(2,numberAnt));
        boolean[] isGoingRight = new boolean[numberAnt];
        for (int i = 0;i < allTimes; i++) {
            int tmp = i;
            for (int j = 0; j < numberAnt; j++) {
                isGoingRight[j] = (tmp % 2) == 0;
                tmp /= 2;
            }
//            for (int k = 0; k < numberAnt; k++) {
//                System.out.print(isGoingRight[k]);
//                System.out.print(",");
//            }
//            System.out.println();
            PlayGame currentGame = new PlayGame(this.numberAnt, this.velocityGroup, isGoingRight, this.position, this.stickLength, this.timeInterval);
            double currentResult = currentGame.startGame();
            if (currentResult > maxTime) {
                this.maxTime = currentResult;
            }
            if (currentResult < minTime) {
                this.minTime = currentResult;
            }
        }

        System.out.println("Max time = " +  this.maxTime);
        System.out.println("Min time = " + this.minTime);
    }
}
