package com.ooad.gof.observer;

/**
 * @author Jiayi Zhu
 * @date 2019-12-02 13:00
 */
public class EmailSubscriber implements Subscriber {
    int value;

    @Override
    public int update(int x) {
        value = x * 2;
        System.out.println("I am output from EmailSubscriber update(), " + value);
        return value;
    }
}
