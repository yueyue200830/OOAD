package com.ecnu.ant;

public class Stick {
    private double length;
    private double deviation = 1e-12;

    Stick (double length) {
        this.length = length;
    }

    public boolean getAntIsOnStick(double antPosition) {
        return antPosition > this.deviation && antPosition < this.length - this.deviation;
    }

    public double getLength() {
        return this.length;
    }
}
