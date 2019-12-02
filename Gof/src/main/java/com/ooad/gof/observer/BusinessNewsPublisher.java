package com.ooad.gof.observer;

import java.util.Vector;

/**
 * @author Jiayi Zhu
 * @date 2019-12-02 13:00
 */
public class BusinessNewsPublisher implements NewsPublisher{
    private Vector<Subscriber> subscribers;
    private int x;

    public BusinessNewsPublisher() {
        super();
        subscribers = new Vector<>();
    }

    @Override
    public void updateAll(int x) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(x);
        }
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void notify(int x) {
        this.x = x;
        this.updateAll(this.x);
    }

}
