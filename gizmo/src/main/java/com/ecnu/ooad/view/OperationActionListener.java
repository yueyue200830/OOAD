package com.ecnu.ooad.view;

import com.ecnu.ooad.Controller;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jiayi Zhu
 * @date 2019-11-18 23:34
 */
public class OperationActionListener implements ActionListener {
    private Controller controller;

    @Contract(pure = true)
    public OperationActionListener(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(@NotNull ActionEvent e) {
        String command = e.getActionCommand();
        if ("rotate".equals(command)) {
            System.out.println("rotate");
            this.controller.rotate();
        } else if ("delete".equals(command)) {
            this.controller.deleteObject();
        } else if ("zoom in".equals(command)) {
            this.manager.changeObjectScale(true);
        } else if ("zoom out".equals(command)) {
            this.manager.changeObjectScale(false);
        }
    }
}
