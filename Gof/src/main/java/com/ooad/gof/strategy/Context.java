package com.ooad.gof.strategy;

import java.util.Scanner;

/**
 * @author Jiayi Zhu
 * @date 2019-12-02 13:34
 */
public class Context {

    public void interact() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input type [ (n)umber, (u)pper, (l)ower, e(x)it ]: ");
        String answer = sc.nextLine();
        DataEntry dataEntry;
        while (!"x".equals(answer)) {
            if ("n".equals(answer)) {
                dataEntry = new DigitEntry();
            } else if ("u".equals(answer)) {
                dataEntry = new UpperEntry();
            } else if ("l".equals(answer)) {
                dataEntry = new LowerEntry();
            } else {
                dataEntry = null;
            }
            if (dataEntry != null) {
                dataEntry.interact();
            }
            System.out.println("Input type [ (n)umber, (u)pper, (l)ower, e(x)it ]: ");
            answer = sc.nextLine();
        }
    }
}
