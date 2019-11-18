package com.ecnu.ooad.view;

import com.ecnu.ooad.Manager;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiayi Zhu
 * @date 2019-11-16 21:09
 */
public class ModePanel extends JPanel {
    private Manager manager;

    public ModePanel(Manager manager) {
        this.manager = manager;
        this.setSize(200, 200);
        this.setVisible(true);
        this.setBackground(Color.yellow);
        this.createMode();
        this.setLayout(new GridLayout(2, 1));
    }

    private void createMode() {
        JButton btnDesign = new JButton("Design Mode");
        JButton btnPlay = new JButton("Play Mode");
        btnDesign.addActionListener(new ModeActionListener(this.manager));
        btnPlay.addActionListener(new ModeActionListener(this.manager));
        this.add(btnDesign);
        this.add(btnPlay);
    }
}
