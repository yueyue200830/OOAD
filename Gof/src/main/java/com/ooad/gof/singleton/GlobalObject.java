package com.ooad.gof.singleton;

/**
 * @author Jiayi Zhu
 * @date 2019-12-02 12:40
 */
public class GlobalObject {

    private int value;
    private static GlobalObject globalObject;

    private GlobalObject() {
        value = 0;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static GlobalObject getInstance() {
        if (globalObject == null) {
            globalObject = new GlobalObject();
        }
        return globalObject;
    }
}
