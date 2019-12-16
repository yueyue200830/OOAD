package com.ooad.gof.command;

/**
 * @author Jiayi Zhu
 * @date 2019-12-16 12:57
 */
public class FanOnCommand implements Command {

    private Fan fan;
    public FanOnCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.startRotate();
    }
}
