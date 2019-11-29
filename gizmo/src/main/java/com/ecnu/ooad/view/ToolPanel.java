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
        this.setBackground(Color.LIGHT_GRAY);
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        this.controller = controller;
        this.initPanel();
    }

    /**
     * Initialize all small panels.
     */
    private void initPanel() {
        Box box = Box.createVerticalBox();
        box.setBackground(Color.LIGHT_GRAY);

        JPanel labelPanel1 = new JPanel();
        JLabel labelIngredient = new JLabel("Ingredient Bar", JLabel.CENTER);
        labelIngredient.setFont(new Font("Calibri", Font.BOLD, 22));
        labelPanel1.add(labelIngredient);
        box.add(labelPanel1);
        box.add(new IngredientPanel(this.controller));

        JPanel labelPanel2 = new JPanel();
        JLabel labelOperation = new JLabel("Operation Bar");
        labelOperation.setFont(new Font("Calibri", Font.BOLD, 22));
        labelPanel2.add(labelOperation);
        box.add(labelPanel2);
        box.add(new OperationPanel(this.controller));

        JPanel labelPanel3 = new JPanel();
        JLabel labelMode = new JLabel("Mode Bar");
        labelMode.setFont(new Font("Calibri", Font.BOLD, 22));
        labelPanel3.add(labelMode);
        box.add(labelPanel3);
        box.add(new ModePanel(this.controller));

        this.add(box);

        gbc.gridx = 0;
        gbc.gridy = 20;
        gbc.gridwidth = 2;
        gbc.gridheight = 8;
        gbc.weightx = 0.5;
        gbc.weighty = 0.8;

        JPanel panel4 = new JPanel();
        panel4.add(new JLabel(new ImageIcon("src/main/resources/ball.png")));
        this.add(panel4, gbc);
    }
}
