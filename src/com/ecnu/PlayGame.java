package com.ecnu;

import java.util.Collection;

public class PlayGame {
    private int antNumber;
    private Ant[] antGroup;
    private double timeInterval;
    private int deadNumber;
    private Stick stick;
    private double timeCost;

    PlayGame (int antNumber, double[] velocity, boolean[] isGoingRight, double[] position, double stickLength, double timeInterval) {
        this.antNumber = antNumber;
        antGroup = new Ant[this.antNumber];
        for (int i = 0; i < this.antNumber; i++) {
            antGroup[i] = new Ant(velocity[i], isGoingRight[i], position[i]);
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
        Ant currentAnt, nextAnt;
        boolean willCollectionWithPrevious = false;
        double previousCollisionTimeConsume = -1;
        double currentCollisionTimeConsume = -1;

        for (int i = 0; i < this.antNumber; i++) {
            currentAnt = this.antGroup[i];

            // If it is the last ant, it doesn't have next ant.
            if (i == this.antNumber - 1) {
                if (willCollectionWithPrevious) {
                    currentAnt.step_collision(this.timeInterval, previousCollisionTimeConsume);
                } else {
                    currentAnt.step_straight(this.timeInterval);
                }
                break;
            } else {
                nextAnt = this.antGroup[i + 1];

                if (willCollectionWithPrevious) {
                    currentAnt.step_collision(this.timeInterval, previousCollisionTimeConsume);
                    willCollectionWithPrevious = false;
                    previousCollisionTimeConsume = -1;
                } else {
                    currentCollisionTimeConsume = checkCollision(currentAnt, nextAnt);
                    if (currentCollisionTimeConsume >= 0) {
                        currentAnt.step_collision(this.timeInterval, currentCollisionTimeConsume);
                        willCollectionWithPrevious = true;
                        previousCollisionTimeConsume = currentCollisionTimeConsume;
                    } else {
                        currentAnt.step_straight(this.timeInterval);
                        willCollectionWithPrevious = false;
                        previousCollisionTimeConsume = -1;
                    }
                }
            }
        }

    }

    // Return collision time if they will collision, otherwise return -1.
    private double checkCollision(Ant currentAnt, Ant nextAnt) {
        double currentAntPosition = currentAnt.getPosition();
        double currentAntVelocity = currentAnt.getVelocity();
        boolean currentAntIsGoingRight = currentAnt.isGoingRight();
        double nextAntPosition = nextAnt.getPosition();
        double nextAntVelocity = nextAnt.getVelocity();
        boolean nextAntIsGoingRight = nextAnt.isGoingRight();
        double collisionTimeConsume;



        return collisionTimeConsume;
    }
}
