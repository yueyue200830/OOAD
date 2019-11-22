package com.ecnu.ooad.view;

import com.ecnu.ooad.Controller;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author Jiayi Zhu
 * @date 2019-11-21 22:31
 */
public class KeyAction extends AbstractAction {
    private Controller controller;

    /**
     * This is the key board listener for track to move.
     * @param actionCommand The command.
     * @param controller Controller.
     */
    public KeyAction(String actionCommand, Controller controller) {
        putValue(ACTION_COMMAND_KEY, actionCommand);
        this.controller = controller;
    }

    /**
     * Set the action to perform.
     * @param actionEvt The action event.
     */
    @Override
    public void actionPerformed(@NotNull ActionEvent actionEvt) {
        if ("VK_LEFT".equals(actionEvt.getActionCommand())) {
            controller.moveHinder(1, true);
        } else if ("VK_RIGHT".equals(actionEvt.getActionCommand())) {
            controller.moveHinder(2, true);
        } else if ("VK_A".equals(actionEvt.getActionCommand())) {
            controller.moveHinder(1, false);
        } else if ("VK_D".equals(actionEvt.getActionCommand())) {
            controller.moveHinder(2, false);
        }
    }
}
