package com.ecnu.ooad;

import com.ecnu.ooad.view.GamePanel;
import com.ecnu.ooad.view.MenuBar;
import com.ecnu.ooad.view.ToolPanel;

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
        frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HIGHT);

        MenuBar bar = new MenuBar();
        GamePanel gamePanel = new GamePanel();
        ToolPanel toolPanel = new ToolPanel(gamePanel.getManager());
        GamePanelMouseListener mouseListener = new GamePanelMouseListener(gamePanel.getManager());
        gamePanel.addMouseListener(mouseListener);
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(toolPanel, BorderLayout.EAST);
        frame.setJMenuBar(bar);
        frame.setVisible(true);

        new Thread(gamePanel).start();
        //new Thread(toolPanel).start();
    }
}
