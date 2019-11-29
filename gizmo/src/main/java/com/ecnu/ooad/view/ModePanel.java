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

    /**
     * This is the panel for mode button.
     * @param controller Game controller.
     */
    public ModePanel(Controller controller) {
        this.controller = controller;
        this.setSize(200, 200);
        this.setVisible(true);
        this.createMode();
        this.setLayout(new GridLayout(2, 1));
    }

    /**
     * Add mode button in the panel.
     */
    private void createMode() {
        JButton btnDesign = new JButton("Design Mode");
        JButton btnPlay = new JButton("Play Mode");
        btnDesign.addActionListener(new ModeActionListener(this.controller));
        btnPlay.addActionListener(new ModeActionListener(this.controller));
        btnDesign.setSize(300, 80);
        btnPlay.setSize(300, 80);
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel1.add(btnDesign);
        panel2.add(btnPlay);
//        this.add(btnDesign);
        this.add(panel1);
        this.add(panel2);
//        this.add(btnPlay);
    }
}
