package com.ecnu;

public class PlayGame {
    private int antNumber;
    private Ant[] antGroup;
    private double timeInterval;
    private int deadNumber;
    private Stick stick;
    private double timeCost;

    PlayGame (int antNumber, double[] velocity, boolean[] isLeft, double[] position, double stickLength, double timeInterval) {
        this.antNumber = antNumber;
        antGroup = new Ant[this.antNumber];
        for (int i = 0; i < this.antNumber; i++) {
            antGroup[i] = new Ant(velocity[i], isLeft[i], position[i]);
        }
        this.deadNumber = 0;
        this.stick = new Stick(stickLength);
        this.timeInterval = timeInterval;
    }

    public double startGame() {
        this.timeCost = 0;
        while (this.deadNumber != this.antNumber) {
            checkDeadAnt();
            if (this.deadNumber != this.antNumber) {
                break;
            }
            this.timeCost += this.timeInterval;
            antsStep();
        }
        return this.timeCost;
    }

    private void checkDeadAnt() {
        for (int i = 0; i < this.antNumber; i++) {
            Ant ant = this.antGroup[i];
            if (ant.isAlive()) {
                double position = ant.getPosition();
                boolean isAlive = this.stick.getAntIsOnStick(position);
                if (!isAlive) {
                    ant.setDie();
                    this.deadNumber++;
                }
            }
        }
    }

    private void antsStep() {
        Ant priviousAnt = this.antGroup[0];
        for (int i = 1; i < this.antNumber; i++) {

        }
    }
}
