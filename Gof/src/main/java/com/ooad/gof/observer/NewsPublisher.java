package com.ooad.gof.observer;

/**
 * @author Jiayi Zhu
 * @date 2019-12-02 12:59
 */
public interface NewsPublisher {
    void updateAll(int x);

    void addSubscriber(Subscriber subscriber);

    void notify(int x);
}
