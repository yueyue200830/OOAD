package com.ecnu;

public class PlayGame {
    private int antNumber;
    private Ant[] antGroup;
    private double timeInterval;

    PlayGame (int antNumber, double[] velocity, boolean[] isLeft) {
        this.antNumber = antNumber;
        antGroup = new Ant[this.antNumber];
        for (int i = 0; i < this.antNumber; i++) {
            antGroup[i] = new Ant();
        }
    }
}
