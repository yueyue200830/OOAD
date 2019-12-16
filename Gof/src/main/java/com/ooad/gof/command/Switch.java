package com.ooad.gof.command;

import java.util.ArrayList;

/**
 * @author Jiayi Zhu
 * @date 2019-12-16 13:04
 */
public class Switch {
    private ArrayList<Command> commands;
    int switchSize = 5;

    public Switch() {
        commands = new ArrayList<>();
        Command noCommand = new NoCommand();
        for(int i = 0; i < switchSize; i++) {
            commands.add(noCommand);
        }
    }

    public void setCommand(int n, Command command) {
        commands.set(n, command);
    }

    public void flipUp() {

    }

    public void flipDown(int n) {
        commands.get(n).execute();
    }
}
