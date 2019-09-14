package com.ecnu;

public class Ant {
    private double velocity;
    private boolean isLeft;
    private double postion;
    private boolean isAlive;
    
    Ant(double veolocity,boolean isLeft,double position){
        this.velocity = velocity;
        this.isLeft = isLeft;
        this.postion = position;
        this.isAlive = true;
    }
    public double  getVelocity(){
        return this.velocity;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public double getPostion() {
        return postion;
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
            this.postion -= this.velocity * timeInterval;
        } else {
            this.postion += this.velocity * timeInterval;
        }
    }
    
    public void step_collision(double timeInterval, double timeConsume) {
        if(this.isLeft) {
            this.postion = this.postion + timeConsume * this.velocity - (timeInterval - timeConsume) * this.velocity;
        } else {
            this.postion = this.postion - timeConsume * this.velocity + (timeInterval - timeConsume) * this.velocity;
        }
    }
    
    public void setDie() {
        this.isAlive = false;
    }
}
