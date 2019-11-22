package com.ecnu.ooad;

import com.ecnu.ooad.view.*;
import com.ecnu.ooad.view.MenuBar;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiayi Zhu
 * @date 2019-11-16 20:30
 */
public class GameApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gizmo Ball");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);

        Controller centralController = new Controller();
        GamePanel gamePanel = new GamePanel(centralController);
        ToolPanel toolPanel = new ToolPanel(centralController);
        MenuBar bar = new MenuBar(centralController);
        GamePanelMouseListener mouseListener = new GamePanelMouseListener(centralController);
        gamePanel.addMouseListener(mouseListener);
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(toolPanel, BorderLayout.EAST);
        frame.setJMenuBar(bar);
        frame.setVisible(true);

        new Thread(gamePanel).start();
    }
}
