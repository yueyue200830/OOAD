package com.ecnu.ooad.view;

import com.ecnu.ooad.Manager;

import javax.swing.*;
import java.util.concurrent.ForkJoinPool;

/**
 * @author Jiayi Zhu
 * @date 2019-11-16 20:54
 */
public class MenuBar extends JMenuBar {
    private Manager manager;

    public MenuBar(Manager manager) {
        this.manager = manager;
        this.newMenu();
        this.setVisible(true);
    }

    public void newMenu() {
        JMenu menu = new JMenu("File");
        JMenuItem item1 = new JMenuItem("New Game");
        JMenuItem item2 = new JMenuItem("Save Game");
        JMenuItem item3 = new JMenuItem("Load Game");
        item1.addActionListener(new MenuListener(this.manager));
        item2.addActionListener(new MenuListener(this.manager));
        item3.addActionListener(new MenuListener(this.manager));
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        this.add(menu);
    }
}
