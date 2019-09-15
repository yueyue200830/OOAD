package com.ecnu;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Ant");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numberAnt = 0;
        double[] velocityGroup = {};
        double[] position = {};
        double stickLength = 0;
        String hint;
       
        try {
            hint = "Please input the number of ant.";
            System.out.println(hint);
            numberAnt = Integer.valueOf(in.readLine());
            while (numberAnt <= 0) {
                hint = "Invalid input. The number of the ant should be larger than 0. Please input again.";
                System.out.println(hint);
                numberAnt = Integer.valueOf(in.readLine());
            }

            hint = "please input the length of the stick";
            System.out.println(hint);
            stickLength = Double.parseDouble(in.readLine());
            while (stickLength <= 0) {
                hint = "Invalid input. Stick length should be larger than 0. Please input again.";
                System.out.println(hint);
                stickLength = Double.parseDouble(in.readLine());
            }

            velocityGroup = new double[numberAnt];
            for (int i = 0; i < numberAnt; i++) {
                hint = "please input the velocity of the " + Integer.toString(i) + " ant";
                System.out.println(hint);
                velocityGroup[i] = Double.parseDouble(in.readLine());
                while (velocityGroup[i] <= 0) {
                    hint = "Invalid input. Please input the velocity again.";
                    System.out.println(hint);
                    velocityGroup[i] = Double.parseDouble(in.readLine());
                }
            }

            position = new double[numberAnt];
            for (int i = 0; i < numberAnt; i++) {
                hint = "please input the position of the " + Integer.toString(i) + " ant";
                System.out.println(hint);
                position[i] = Double.parseDouble(in.readLine());
                while (position[i] < 0 || position[i] > stickLength) {
                    hint = "Invalid input. Please input the position again.";
                    System.out.println(hint);
                    position[i] = Double.parseDouble(in.readLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        GameControl gc = new GameControl(numberAnt, velocityGroup, 0.01, position, stickLength);
        gc.enumerateGame();
    }
}
