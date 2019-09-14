package com.ecnu;

public class Stick {
    private double length;

    Stick (double length) {
        this.length = length;
    }

    public boolean getAntIsOnStick(double antPosition) {
        return antPosition <= 0 && antPosition >= this.length;
    }

    public double getLength() {
        return this.length;
    }
}
