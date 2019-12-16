package com.ooad.gof.command;

/**
 * @author Jiayi Zhu
 * @date 2019-12-16 13:03
 */
public class LightOnCommand implements Command  {
    private Light light;
    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}
