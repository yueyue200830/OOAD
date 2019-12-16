package com.ooad.gof.factory;

/**
 * @author Jiayi Zhu
 * @date 2019-12-16 12:42
 */
public class FactoryA implements Factory {

    @Override
    public Calculate createOperate(String type, double a, double b) {
        Calculate cal = null;
        if ("A".equals(type)) {
            cal = new Add(a, b);
        } else if ("S".equals(type)) {
            cal = new Subject(a, b);
        } else if ("M".equals(type)) {
            cal = new Multiply(a, b);
        } else if ("D".equals(type)) {
            cal = new Divide(a, b);
        }
        return cal;
    }
}
