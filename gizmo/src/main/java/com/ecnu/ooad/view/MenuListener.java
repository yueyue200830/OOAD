package com.ecnu.ooad.view;

import com.ecnu.ooad.Controller;
import com.ecnu.ooad.utils.TransformUtil;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

/**
 * @author Yiqing Tao
 * @date 2019-11-20 19:31
 */
public class MenuListener implements ActionListener {
    private Controller controller;

    @Contract(pure = true)
    public MenuListener(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(@NotNull ActionEvent e) {
        String command = e.getActionCommand();
        if ("New Game".equals(command)) {
            System.out.println("new game");
            this.controller.newGame();
        } else if ("Save Game".equals(command)) {
            System.out.println("save game");
            this.controller.saveGame();
        } else if ("Load Game".equals(command)) {
            System.out.println("load game");
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jfc.showDialog(new JLabel(), "choose file");
            File file = jfc.getSelectedFile();
            if (file.isFile()) {
                System.out.println("choose a file");
                System.out.println(file.getName());
                this.controller.loadGame(file.getName());
            } else {
                System.out.println("not a file");
            }
        }
    }
}
