package com.ooad.gof.strategy;

/**
 * @author Jiayi Zhu
 * @date 2019-12-02 13:30
 */
public interface DataEntry {

    void interact();
    boolean validate(String answer);
}
