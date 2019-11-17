package com.ecnu.ooad.view;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.Manager;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-16 10:56
 */
public class GamePanel extends JPanel implements Runnable {
    private Manager manager;

    public GamePanel() {
        this.setSize(Constants.GAME_WIDTH, Constants.GAME_HIGHT);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        //manager = new Manager();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.manager.draw((Graphics2D) g);
        g.dispose();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(30);
                this.repaint();
                this.manager.step();
                System.out.println("run!!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
