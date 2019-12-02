package com.ooad.gof.strategy;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * @author Jiayi Zhu
 * @date 2019-12-02 13:46
 */
public class UpperEntry implements DataEntry {

    @Override
    public void interact() {
        String answer;
        Scanner sc = new Scanner(System.in);
        System.out.print("Promot: ");
        answer = sc.nextLine();
        while (!"quit".equals(answer)) {
            if (validate(answer)) {
                System.out.println("*** good ***");
            } else {
                System.out.println("*** bad ***");
            }
            System.out.print("Promot: ");
            answer = sc.nextLine();
        }
    }

    @Override
    public boolean validate(@NotNull String answer) {
        boolean valid = true;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) < 'A' || answer.charAt(i) > 'Z') {
                valid = false;
                break;
            }
        }
        return valid;
    }
}
