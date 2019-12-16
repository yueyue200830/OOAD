package com.ooad.gof.factory;

/**
 * @author Jiayi Zhu
 * @date 2019-12-16 12:42
 */
public interface Factory {
    Calculate createOperate(String type, double a, double b);
}
