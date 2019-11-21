package com.ecnu.ooad.view;

import com.ecnu.ooad.Controller;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jiayi Zhu
 * @date 2019-11-18 23:41
 */
public class ModeActionListener implements ActionListener {
    private Controller controller;

    @Contract(pure = true)
    public ModeActionListener(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(@NotNull ActionEvent e) {
        String command = e.getActionCommand();
        if ("Design Mode".equals(command)) {
            this.controller.setPlayMode(false);
        } else if ("Play Mode".equals(command)) {
            this.controller.setPlayMode(true);
        }
    }
}
