package com.ooad.gof.factory;

/**
 * @author Jiayi Zhu
 * @date 2019-12-16 13:29
 */
public abstract class Calculate {
    double numberA;
    double numberB;

    public Calculate(double a, double b) {
        numberA = a;
        numberB = b;
    }

    abstract void getResult();
}
