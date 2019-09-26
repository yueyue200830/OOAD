package com.ecnu.ant;

public class GameControl {
    private int numberAnt;
    private double[] velocityGroup;
    private double timeInterval;
    private double[] position;
    private double stickLength;
    private double minTime;
    private double maxTime;
    private boolean[] minTimeDirection;
    private boolean[] maxTimeDirection;

    GameControl(int numberAnt, double[] velocityGroup, double timeInterval, double[] position, double stickLength) {
        this.numberAnt = numberAnt;
        this.velocityGroup = velocityGroup;
        this.timeInterval = timeInterval;
        this.position = position;
        this.stickLength = stickLength;
        this.minTime = Double.POSITIVE_INFINITY;
        this.maxTime = 0;
        String resultShow = "Now create a GameControl Object with numberAnt = " + Integer.toString(numberAnt) + " and a stick length " + Double.toString(stickLength);
        System.out.println(resultShow);
    }

    public void enumerateGame() {
        int allTimes = (int) (Math.pow(2, numberAnt));
        boolean[] isGoingRight = new boolean[numberAnt];
        for (int i = 0; i < allTimes; i++) {
            int tmp = i;
            for (int j = 0; j < numberAnt; j++) {
                isGoingRight[j] = (tmp % 2) == 0;
                tmp /= 2;
            }

            PlayGame currentGame = new PlayGame(this.numberAnt, this.velocityGroup, isGoingRight, this.position, this.stickLength, this.timeInterval);
            double currentResult = currentGame.startGame();
            if (currentResult > maxTime) {
                this.maxTime = currentResult;
                this.maxTimeDirection = isGoingRight.clone();
            }
            if (currentResult < minTime) {
                this.minTime = currentResult;
                this.minTimeDirection = isGoingRight.clone();
            }
        }

        System.out.printf("Max time = %.2fs.\n", this.maxTime);
        System.out.printf("Min time = %.2fs.\n", this.minTime);
    }

    public double getMinTime() {
        return this.minTime;
    }

    public double getMaxTime() {
        return this.maxTime;
    }

    public boolean[] getMinTimeDirection() {
        return this.minTimeDirection.clone();
    }

    public boolean[] getMaxTimeDirection() {
        return this.maxTimeDirection.clone();
    }
}
