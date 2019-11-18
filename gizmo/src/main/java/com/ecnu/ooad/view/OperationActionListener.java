package com.ecnu.ooad.view;

import com.ecnu.ooad.Manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jiayi Zhu
 * @date 2019-11-18 23:34
 */
public class OperationActionListener implements ActionListener {
    private Manager manager;

    public OperationActionListener(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
