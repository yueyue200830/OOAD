package com.ooad.gof.command;

/**
 * @author Jiayi Zhu
 * @date 2019-12-16 12:59
 */
public class FanOffCommand implements Command {
    private Fan fan;
    public FanOffCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void execute() {
        fan.stopRotate();
    }
}
