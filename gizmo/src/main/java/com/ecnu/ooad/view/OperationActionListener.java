package com.ecnu.ooad.view;

import com.ecnu.ooad.Manager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jiayi Zhu
 * @date 2019-11-18 23:34
 */
public class OperationActionListener implements ActionListener {
    private Manager manager;

    @Contract(pure = true)
    public OperationActionListener(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void actionPerformed(@NotNull ActionEvent e) {
        String command = e.getActionCommand();
        if ("rotate".equals(command)) {
            System.out.println("rotate");
            this.manager.rotate();
        } else if ("delete".equals(command)) {
            // somthing
        } else if ("zoom in".equals(command)) {
            // somthing
        } else if ("zoom out".equals(command)) {
            // somthing
        }
    }
}
