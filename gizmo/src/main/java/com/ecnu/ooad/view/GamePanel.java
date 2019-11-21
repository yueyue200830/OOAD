package com.ecnu.ooad.view;

import com.ecnu.ooad.Controller;
import com.ecnu.ooad.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;

/**
 * @author Jiayi Zhu
 * @date 2019-11-16 20:31
 */
public class GamePanel extends JPanel implements Runnable {
    private Controller controller;

    public GamePanel(Controller controller) {
        this.setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        this.setVisible(true);
        this.setBackground(Color.black);
        this.controller = controller;
        this.setHinderAction();
    }

    private void paintLines(Graphics2D g) {
        Line2D lin;
        for (int i = 1; i < Constants.GAME_WIDTH / Constants.GRID_LENGTH; i++) {
            lin = new Line2D.Float(0, Constants.GRID_LENGTH * i, Constants.GAME_WIDTH, Constants.GRID_LENGTH * i);
            g.draw(lin);

            lin = new Line2D.Float(Constants.GRID_LENGTH * i, 0, Constants.GRID_LENGTH * i, Constants.GAME_WIDTH);
            g.draw(lin);
        }
    }

    private void setHinderAction() {
        ActionMap actionMap = getActionMap();
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        String vkLeft = "VK_LEFT";
        String vkRight = "VK_RIGHT";
        String vkA = "VK_A";
        String vkD = "VK_D";
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), vkLeft);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), vkRight);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), vkA);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), vkD);

        actionMap.put(vkLeft, new KeyAction(vkLeft, controller));
        actionMap.put(vkRight, new KeyAction(vkRight, controller));
        actionMap.put(vkA, new KeyAction(vkA, controller));
        actionMap.put(vkD, new KeyAction(vkD, controller));

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (!this.controller.isPlayMode()) {
            paintLines((Graphics2D) g);
        }
        controller.draw((Graphics2D) g);
        g.dispose();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(30);
                this.repaint();
                if (this.controller.isPlayMode()) {
                    controller.step();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
