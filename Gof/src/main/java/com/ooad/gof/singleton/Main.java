package com.ooad.gof.singleton;

/**
 * @author Jiayi Zhu
 * @date 2019-12-02 12:42
 */
public class Main {
    public static void main(String[] args) {
        foo();
        bar();
        GlobalObject globalObject = GlobalObject.getInstance();
        System.out.println("main: globalObject's value is " + globalObject.getValue());
    }

    public static void foo() {
        GlobalObject globalObject = GlobalObject.getInstance();
        System.out.println("foo: globalObject's value is " + globalObject.getValue());
    }

    public static void bar() {
        GlobalObject globalObject = GlobalObject.getInstance();
        globalObject.setValue(42);
        System.out.println("bar: globalObject's value is " + globalObject.getValue());
    }
}
