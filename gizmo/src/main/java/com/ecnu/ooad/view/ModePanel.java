package com.ecnu.ooad.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiayi Zhu
 * @date 2019-11-16 21:09
 */
public class ModePanel extends JPanel {
    public ModePanel() {
        this.setSize(200, 200);
        this.setVisible(true);
        this.setBackground(Color.yellow);
        this.createMode();
        this.setLayout(new GridLayout(2, 1));
    }

    private void createMode() {
        JButton btnDesign = new JButton("Design Mode");
        JButton btnPlay = new JButton("Play Mode");
        this.add(btnDesign);
        this.add(btnPlay);
    }
}
