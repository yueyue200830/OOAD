package com.ecnu;

public class Ant {
    private double velocity;
    private boolean isLeft;
    private double position;
    private boolean isAlive;
    
    Ant(double velocity, boolean isLeft, double position){
        this.velocity = velocity;
        this.isLeft = isLeft;
        this.position = position;
        this.isAlive = true;
    }
    public double  getVelocity(){
        return this.velocity;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public double getPosition() {
        return position;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void changeDirection() {
        if (this.isLeft) {
            this.isLeft = false;
        } else {
            this.isLeft = true;
        }
    }
    
    public void step_straight(double timeInterval) {
        if (this.isLeft) {
            this.position -= this.velocity * timeInterval;
        } else {
            this.position += this.velocity * timeInterval;
        }
    }
    
    public void step_collision(double timeInterval, double timeConsume) {
        if(this.isLeft) {
            this.position = this.position + timeConsume * this.velocity - (timeInterval - timeConsume) * this.velocity;
        } else {
            this.position = this.position - timeConsume * this.velocity + (timeInterval - timeConsume) * this.velocity;
        }
    }
    
    public void setDie() {
        this.isAlive = false;
    }
}
