package com.ecnu.ooad.view;

import com.ecnu.ooad.Manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jiayi Zhu
 * @date 2019-11-18 23:41
 */
public class ModeActionListener implements ActionListener {
    private Manager manager;

    public ModeActionListener(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("Design Mode".equals(command)) {
            this.manager.setIsPlayMode(false);
        } else if ("Play Mode".equals(command)) {
            this.manager.setIsPlayMode(true);
        }
    }
}
