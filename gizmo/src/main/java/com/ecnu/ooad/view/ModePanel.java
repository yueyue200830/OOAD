package com.ecnu.ooad.view;

import com.ecnu.ooad.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiayi Zhu
 * @date 2019-11-16 21:09
 */
public class ModePanel extends JPanel {
    private Controller controller;

    public ModePanel(Controller controller) {
        this.controller = controller;
        this.setSize(200, 200);
        this.setVisible(true);
        this.setBackground(Color.yellow);
        this.createMode();
        this.setLayout(new GridLayout(2, 1));
    }

    private void createMode() {
        JButton btnDesign = new JButton("Design Mode");
        JButton btnPlay = new JButton("Play Mode");
        btnDesign.addActionListener(new ModeActionListener(this.controller));
        btnPlay.addActionListener(new ModeActionListener(this.controller));
        this.add(btnDesign);
        this.add(btnPlay);
    }
}
