package com.ooad.gof.command;

/**
 * @author Jiayi Zhu
 * @date 2019-12-16 13:03
 */
public class LightOffCommand implements Command {
    private Light light;
    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}
