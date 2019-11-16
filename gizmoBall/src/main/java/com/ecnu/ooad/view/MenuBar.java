package com.ecnu.ooad.view;

import javax.swing.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-16 11:51
 */
public class MenuBar extends JMenuBar {
    public MenuBar() {
        this.newMenu();
        this.setVisible(true);
    }

    public void newMenu() {
        JMenu menu = new JMenu("File");
        JMenuItem item1 = new JMenuItem("New Game");
        JMenuItem item2 = new JMenuItem("Save Game");
        JMenuItem item3 = new JMenuItem("Load Game");
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        this.add(menu);
    }
}
