package com.ecnu.ooad.view;

import com.ecnu.ooad.Manager;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiayi Zhu
 * @date 2019-11-16 21:09
 */
public class OperationPanel extends JPanel {
    private Manager manager;

    public OperationPanel(Manager manager) {
        this.manager = manager;
        this.setSize(200, 200);
        this.setVisible(true);
        this.setBackground(Color.green);
        this.createButtons();
        this.setLayout(new GridLayout(2, 2));
    }

    private void createButtons() {
        JButton btnRotate = new JButton("rotate");
        JButton btnDelete = new JButton("delete");
        JButton btnZoomIn = new JButton("zoom in");
        JButton btnZoomOut = new JButton("zoom out");
        btnRotate.addActionListener(new OperationActionListener(this.manager));
        btnDelete.addActionListener(new OperationActionListener(this.manager));
        btnZoomIn.addActionListener(new OperationActionListener(this.manager));
        btnZoomOut.addActionListener(new OperationActionListener(this.manager));
        this.add(btnRotate);
        this.add(btnDelete);
        this.add(btnZoomIn);
        this.add(btnZoomOut);
    }
}
