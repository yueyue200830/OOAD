package com.ecnu.ooad.view;

import com.ecnu.ooad.Controller;

import javax.swing.*;

/**
 * @author Jiayi Zhu
 * @date 2019-11-16 20:54
 */
public class MenuBar extends JMenuBar {
    private Controller controller;

    /**
     * The is the menu of the game.
     * @param controller Game controller.
     */
    public MenuBar(Controller controller) {
        this.controller = controller;
        this.newMenu();
        this.setVisible(true);
    }

    /**
     * Add menu items.
     */
    public void newMenu() {
        JMenu menu = new JMenu("File");
        JMenuItem item1 = new JMenuItem("New Game");
        JMenuItem item2 = new JMenuItem("Save Game");
        JMenuItem item3 = new JMenuItem("Load Game");
        item1.addActionListener(new MenuListener(this.controller));
        item2.addActionListener(new MenuListener(this.controller));
        item3.addActionListener(new MenuListener(this.controller));
        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        this.add(menu);
    }
}
