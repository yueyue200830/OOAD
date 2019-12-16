package com.ooad.gof.factory;

/**
 * @author Jiayi Zhu
 * @date 2019-12-16 13:31
 */
public class Multiply extends Calculate {
    public Multiply(double a, double b) {
        super(a, b);
    }
    @Override
    public void getResult() {
        System.out.print("multiply = ");
        System.out.println(numberA * numberB);
    }
}
