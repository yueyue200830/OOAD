package com.ooad.gof.factory;

/**
 * @author Jiayi Zhu
 * @date 2019-12-16 13:32
 */
public class Subject extends Calculate {
    public Subject(double a, double b) {
        super(a, b);
    }
    @Override
    public void getResult() {
        System.out.print("subject = ");
        System.out.println(numberA - numberB);
    }
}
