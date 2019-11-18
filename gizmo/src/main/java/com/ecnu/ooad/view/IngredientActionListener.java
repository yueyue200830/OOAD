package com.ecnu.ooad.view;

import com.ecnu.ooad.Manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Yiqing Tao
 * @date 2019-11-17 12:51
 */
public class IngredientActionListener implements ActionListener {
    private Manager manager;

    public IngredientActionListener(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("mouse".equals(command)) {
            System.out.println("mouse section");
            this.manager.setIngredientCondition(0);
        } else if ("ball".equals(command)) {
            this.manager.setIngredientCondition(1);
        } else if ("absorber".equals(command)) {
            this.manager.setIngredientCondition(2);

        } else if ("slope".equals(command)) {
            this.manager.setIngredientCondition(3);

        } else if ("diamond".equals(command)) {
            this.manager.setIngredientCondition(4);

        } else if ("emerald".equals(command)) {
            this.manager.setIngredientCondition(5);

        } else if ("straightTrack".equals(command)) {
            this.manager.setIngredientCondition(6);

        } else if ("curveTrack".equals(command)) {
            this.manager.setIngredientCondition(7);

        } else if ("hinderLeft".equals(command)) {
            this.manager.setIngredientCondition(8);

        } else if ("hinderRight".equals(command)) {
            this.manager.setIngredientCondition(9);

        }
    }
}
