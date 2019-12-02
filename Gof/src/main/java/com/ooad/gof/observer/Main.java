package com.ooad.gof.observer;

/**
 * @author Jiayi Zhu
 * @date 2019-12-02 12:59
 */
public class Main {
    public static void main(String[] args) {
        NewsPublisher business = new BusinessNewsPublisher();
        business.addSubscriber(new SMSSubscriber());
        business.addSubscriber(new EmailSubscriber());
        business.notify(1);
        business.notify(2);
    }
}
