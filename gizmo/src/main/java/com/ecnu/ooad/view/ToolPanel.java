package com.ecnu.ooad.view;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.Manager;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiayi Zhu
 * @date 2019-11-16 21:06
 */
public class ToolPanel extends JPanel {
    private GridBagConstraints gbc;
    private Manager manager;

    public ToolPanel(Manager manager) {
        this.setSize(Constants.TOOL_WIDTH, Constants.TOOL_HIGHT);
        this.setVisible(true);
        this.setBackground(Color.red);
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        this.manager = manager;
        this.initPanel();
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    private void initPanel() {
        JLabel label = new JLabel("Ingredient Bar");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.8;
        this.add(label, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 11;
        gbc.weightx = 0.5;
        gbc.weighty = 0.8;
        this.add(new IngredientPanel(this.manager), gbc);

        JLabel labelOperation = new JLabel("Operation Bar");
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.8;
        this.add(labelOperation, gbc);
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.gridwidth = 2;
        gbc.gridheight = 5;
        gbc.weightx = 0.5;
        gbc.weighty = 0.8;
        this.add(new OperationPanel(this.manager), gbc);

        JLabel labelMode = new JLabel("Mode Bar");
        gbc.gridx = 0;
        gbc.gridy = 18;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.8;
        this.add(labelMode, gbc);
        gbc.gridx = 0;
        gbc.gridy = 19;
        gbc.gridwidth = 2;
        gbc.gridheight = 8;
        gbc.weightx = 0.5;
        gbc.weighty = 0.8;
        this.add(new ModePanel(this.manager), gbc);
    }
}
