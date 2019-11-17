package com.ecnu.ooad.view;

import com.ecnu.ooad.GamePanelMouseListener;
import com.ecnu.ooad.Manager;
import com.ecnu.ooad.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiayi Zhu
 * @date 2019-11-16 20:31
 */
public class GamePanel extends JPanel implements Runnable {
    private Manager manager;
    private GamePanelMouseListener mouseListener;

    public GamePanel() {
        this.setSize(Constants.GAME_WIDTH, Constants.GAME_HIGHT);
        this.setVisible(true);
        this.setBackground(Color.black);
        this.manager = new Manager();
    }

    public Manager getManager() {
        return this.manager;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        manager.draw((Graphics2D) g);
        g.dispose();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(30);
                this.repaint();
                manager.step();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
