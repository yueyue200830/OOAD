package com.ecnu;
import java.io.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello Ant");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numberAnt = 0;
        double[] velocityGroup = {};
        double[] position = {};
        double stickLength = 0;
       
        try {
            System.out.println("Please input the number of ant");
            numberAnt = Integer.valueOf(in.readLine());
            velocityGroup = new double[numberAnt];
            position = new double[numberAnt];
            for(int i = 0; i< numberAnt; i++) {
                String hint = "please input the velocity of the " + Integer.toString(i) + " ant";
                System.out.println(hint);
                velocityGroup[i] = Double.parseDouble(in.readLine());
            }
            for(int i = 0; i< numberAnt; i++) {
                String hint = "please input the position of the " + Integer.toString(i) + " ant";
                System.out.println(hint);
                position[i] = Double.parseDouble(in.readLine());
            }
            String hint = "please input the length of the stick";
            System.out.println(hint);
            stickLength = Double.parseDouble(in.readLine());
        } catch(IOException e){
            e.printStackTrace();
        }
        GameControl gc = new GameControl(numberAnt, velocityGroup, 0.01, position, stickLength);
        gc.enumerateGame();
    }
}
