package com.ooad.gof.strategy2;

import java.util.Scanner;

/**
 * @author Jiayi Zhu
 * @date 2019-12-16 13:24
 */
public class Context {

    public void interact() {
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        Sort sort;
        while (!"4".equals(answer)) {
            if ("1".equals(answer)) {
                sort = new shellSort();
                sort.doSort();
            } else if ("2".equals(answer)) {
                sort = new quickSort();
                sort.doSort();
            } else if ("3".equals(answer)) {
                sort = new insertSort();
                sort.doSort();
            }
            answer = sc.nextLine();
        }
        System.out.println("Bye");
    }
}
