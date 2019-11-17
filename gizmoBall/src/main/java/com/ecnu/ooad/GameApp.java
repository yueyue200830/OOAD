package com.ecnu.ooad;

import com.ecnu.ooad.view.*;
import com.ecnu.ooad.view.MenuBar;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-16 11:02
 */
public class GameApp {
    public static void main(String[] args) {
        System.out.println("hello gizmo ball");
        JFrame frame = new JFrame("gizmo ball");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.FRAME_WIDTH , Constants.FRAME_HIGHT);

        Manager manager = new Manager();
        MenuBar bar = new MenuBar();
        GamePanel gamePanel = new GamePanel();
        gamePanel.setManager(manager);
        //ToolPanel toolPanel = new ToolPanel();
        frame.add(gamePanel, BorderLayout.CENTER);
       // frame.add(toolPanel, BorderLayout.EAST);
        frame.setJMenuBar(bar);
        frame.setVisible(true);
        //new Thread(gamePanel).start();
    }
}
