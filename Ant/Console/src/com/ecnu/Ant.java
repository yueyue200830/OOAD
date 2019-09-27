package com.ecnu;

public class Ant {
    private double velocity;
    private boolean isGoingRight;
    private double position;
    private boolean isAlive;

    Ant(double velocity, boolean isGoingRight, double position){
        this.velocity = velocity;
        this.isGoingRight = isGoingRight;
        this.position = position;
        this.isAlive = true;
    }
    public double getVelocity(){
        return this.velocity;
    }

    public boolean isGoingRight() {
        return this.isGoingRight;
    }

    public double getPosition() {
        return this.position;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    private void changeDirection() {
        if (this.isGoingRight) {
            this.isGoingRight = false;
        } else {
            this.isGoingRight = true;
        }
    }

    public void step_straight(double timeInterval) {
        if (this.isGoingRight) {
            this.position += this.velocity * timeInterval;
        } else {
            this.position -= this.velocity * timeInterval;
        }
    }

    public void step_collision(double timeInterval, double timeConsume) {
        if (this.isGoingRight) {
            this.position += timeConsume * this.velocity - (timeInterval - timeConsume) * this.velocity;
        } else {
            this.position -= timeConsume * this.velocity - (timeInterval - timeConsume) * this.velocity;
        }
        this.changeDirection();
    }

    public void setDie() {
        this.isAlive = false;
    }
}
