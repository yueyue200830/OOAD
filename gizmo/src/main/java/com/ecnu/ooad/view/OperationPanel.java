package com.ecnu.ooad.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiayi Zhu
 * @date 2019-11-16 21:09
 */
public class OperationPanel extends JPanel {
    public OperationPanel() {
        this.setSize(200,200);
        this.setVisible(true);
        this.setBackground(Color.green);
        this.createButtons();
        this.setLayout(new GridLayout(2, 2));
    }

    private void createButtons() {
        JButton btnZoomIn = new JButton("zoom in");
        JButton btnZoomOut = new JButton("zoom out");
        JButton btnDelete = new JButton("delete");
        JButton btnRotate = new JButton("rotate");
        this.add(btnRotate);
        this.add(btnDelete);
        this.add(btnZoomIn);
        this.add(btnZoomOut);
    }
}
