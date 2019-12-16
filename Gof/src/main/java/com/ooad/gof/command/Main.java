package com.ooad.gof.command;

import java.util.Scanner;

/**
 * @author Jiayi Zhu
 * @date 2019-12-16 12:38
 */
public class Main {
    public static void main(String[] args) {

        Fan fan = new Fan();
        Light light = new Light();
        Command fanCommand1 = new FanOnCommand(fan);
        Command fanCommand2 = new FanOffCommand(fan);
        Command lightCommand1 = new LightOnCommand(light);
        Command lightCommand2 = new LightOffCommand(light);

        Switch s = new Switch();

        s.setCommand(1, fanCommand1);
        s.setCommand(2, fanCommand2);
        s.setCommand(3, lightCommand1);
        s.setCommand(4, lightCommand2);


        Scanner sc = new Scanner(System.in);
        String answer;
        answer = sc.nextLine();
        while (!"5".equals(answer)) {
            if ("1".equals(answer)) {
                s.flipDown(1);
            } else if ("2".equals(answer)) {
                s.flipDown(2);
            } else if ("3".equals(answer)) {
                s.flipDown(3);
            } else if ("4".equals(answer)) {
                s.flipDown(4);
            }
            sc = new Scanner(System.in);
            answer = sc.nextLine();
        }
        System.out.println("bye!");
    }
}
