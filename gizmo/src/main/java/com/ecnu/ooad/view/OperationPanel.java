package com.ecnu.ooad.view;

import com.ecnu.ooad.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiayi Zhu
 * @date 2019-11-16 21:09
 */
public class OperationPanel extends JPanel {
    private Controller controller;

    /**
     * This is the panel for some operation button.
     * @param controller Game controller.
     */
    public OperationPanel(Controller controller) {
        this.controller = controller;
        this.setSize(200, 200);
        this.setVisible(true);
        this.setBackground(Color.green);
        this.createButtons();
        this.setLayout(new GridLayout(2, 2));
    }

    /**
     * Add operation button in the panel.
     */
    private void createButtons() {
        JButton btnRotate = new JButton("rotate");
        JButton btnDelete = new JButton("delete");
        JButton btnZoomIn = new JButton("zoom in");
        JButton btnZoomOut = new JButton("zoom out");
        btnRotate.addActionListener(new OperationActionListener(this.controller));
        btnDelete.addActionListener(new OperationActionListener(this.controller));
        btnZoomIn.addActionListener(new OperationActionListener(this.controller));
        btnZoomOut.addActionListener(new OperationActionListener(this.controller));
        this.add(btnRotate);
        this.add(btnDelete);
        this.add(btnZoomIn);
        this.add(btnZoomOut);
    }
}
