package com.ecnu.ooad.view;

import com.ecnu.ooad.GamePanelMouseListener;
import com.ecnu.ooad.Manager;
import com.ecnu.ooad.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

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

    private void paintLines(Graphics2D g) {
        Line2D lin;
        for (int i = 1; i < 600 / 20; i++) {
            lin = new Line2D.Float(0, 20 * i, 600, 20 * i);
            g.draw(lin);

            lin = new Line2D.Float(20 * i, 0, 20 * i, 600);
            g.draw(lin);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (!this.manager.isPlayMode()) {
            paintLines((Graphics2D) g);
        }
        manager.draw((Graphics2D) g);
        g.dispose();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(30);
                this.repaint();
                if (this.manager.isPlayMode()) {
                    manager.step();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
