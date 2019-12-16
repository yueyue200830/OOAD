package com.ooad.gof.factory;

/**
 * @author Jiayi Zhu
 * @date 2019-12-16 13:32
 */
public class Divide extends Calculate {
    public Divide(double a, double b) {
        super(a, b);
    }
    @Override
    public void getResult() {
        System.out.print("divide = ");
        System.out.println(numberA / numberB);
    }
}
