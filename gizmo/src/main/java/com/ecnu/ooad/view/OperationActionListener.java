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

    /**
     * This is listener for operation panel.
     * @param controller Game controller.
     */
    @Contract(pure = true)
    public OperationActionListener(Controller controller) {
        this.controller = controller;
    }

    /**
     * Deal with the button action.
     * @param e Action event.
     */
    @Override
    public void actionPerformed(@NotNull ActionEvent e) {
        String command = e.getActionCommand();

        if (OperationPanel.imgRotate.equals(command)) {
            System.out.println("rotate");
            this.controller.rotate();
        } else if (OperationPanel.imgDelete.equals(command)) {
            this.controller.deleteObject();
        } else if (OperationPanel.imgZoomIn.equals(command)) {
            this.controller.changeObjectScale(true);
        } else if (OperationPanel.imgZoomOut.equals(command)) {
            this.controller.changeObjectScale(false);
        }
    }
}
