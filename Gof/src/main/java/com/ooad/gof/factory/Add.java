package com.ooad.gof.factory;

/**
 * @author Jiayi Zhu
 * @date 2019-12-16 13:29
 */
public class Add extends Calculate {
    public Add(double a, double b) {
        super(a, b);
    }

    @Override
    public void getResult() {
        System.out.print("add = ");
        System.out.println(numberA + numberB);
    }
}
