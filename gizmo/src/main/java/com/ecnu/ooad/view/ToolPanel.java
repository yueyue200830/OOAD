package com.ecnu.ooad.view;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.Controller;
import com.ecnu.ooad.utils.IngredientCondition;

import javax.swing.*;
import javax.swing.text.IconView;
import java.awt.*;

/**
 * @author Jiayi Zhu
 * @date 2019-11-16 21:06
 */
public class ToolPanel extends JPanel {
    private GridBagConstraints gbc;
    private Controller controller;

    /**
     * This is the panel for all tools.
     * @param controller Game controller.
     */
    public ToolPanel(Controller controller) {
        this.setSize(Constants.TOOL_WIDTH, Constants.TOOL_HEIGHT);
        this.setVisible(true);
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        this.controller = controller;
        this.initPanel();
    }

    /**
     * Initialize all small panels.
     */
    private void initPanel() {
        JPanel labelPanel1 = new JPanel();
        JLabel labelIngredient = new JLabel("Ingredient Bar");
        labelPanel1.add(labelIngredient);
        Box vBox1 = Box.createVerticalBox();
        vBox1.add(labelPanel1);
        vBox1.add(new IngredientPanel(this.controller));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.8;
        this.add(vBox1, gbc);
//        labelPanel1.add(new IngredientPanel(this.controller));
//        this.add(labelPanel1, gbc);
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        gbc.gridwidth = 2;
//        gbc.gridheight = 11;
//        gbc.weightx = 0.5;
//        gbc.weighty = 0.8;
//        this.add(new IngredientPanel(this.controller), gbc);

        JPanel labelPanel2 = new JPanel();
        JLabel labelOperation = new JLabel("Operation Bar");
        labelPanel2.add(labelOperation);
        Box vBox2 = Box.createVerticalBox();
        vBox2.add(labelPanel2);
        vBox2.add(new OperationPanel(this.controller));
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.8;
        this.add(vBox2, gbc);
//        this.add(labelOperation, gbc);
//        gbc.gridx = 0;
//        gbc.gridy = 13;
//        gbc.gridwidth = 2;
//        gbc.gridheight = 5;
//        gbc.weightx = 0.5;
//        gbc.weighty = 0.8;
//        this.add(new OperationPanel(this.controller), gbc);

        JPanel labelPanel3 = new JPanel();
        JLabel labelMode = new JLabel("Mode Bar");
        labelPanel3.add(labelMode);
        Box vBox3 = Box.createVerticalBox();
        vBox3.add(labelPanel3);
        vBox3.add(new ModePanel(this.controller));
        gbc.gridx = 0;
        gbc.gridy = 18;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.8;
        this.add(vBox3, gbc);
//        this.add(labelMode, gbc);
        gbc.gridx = 0;
        gbc.gridy = 20;
        gbc.gridwidth = 2;
        gbc.gridheight = 8;
        gbc.weightx = 0.5;
        gbc.weighty = 0.8;
//        this.add(new ModePanel(this.controller), gbc);

        JPanel panel4 = new JPanel();
        panel4.add(new JLabel(new ImageIcon("hole.png")));
        this.add(panel4, gbc);
    }
}
