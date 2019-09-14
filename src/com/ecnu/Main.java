package com.ecnu;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello Ant");
        double[] velocityGroup = {2,3,4,5,6};
        double[] position = {10,90,150,230,300};
        GameControl gc = new GameControl(3, velocityGroup, 0.01, position, 320);
        gc.enumerateGame();
    }
}
