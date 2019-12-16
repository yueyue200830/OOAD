package com.ooad.gof.command;

/**
 * @author Jiayi Zhu
 * @date 2019-12-16 12:42
 */
public class NoCommand implements Command {
    @Override
    public void execute() {
        System.out.println("nothing happened");
    }
}
