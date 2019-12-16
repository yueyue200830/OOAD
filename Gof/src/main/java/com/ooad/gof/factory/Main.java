package com.ooad.gof.factory;

/**
 * @author Jiayi Zhu
 * @date 2019-12-16 12:42
 */
public class Main {
    public static void main(String[] args) {
        double a = 6;
        double b = 3;
        Factory factory = new FactoryA();
        Calculate cal = factory.createOperate("A", a, b);
        cal.getResult();
        cal = factory.createOperate("S", a, b);
        cal.getResult();
        cal = factory.createOperate("M", a, b);
        cal.getResult();
        cal = factory.createOperate("D", a, b);
        cal.getResult();
    }
}
