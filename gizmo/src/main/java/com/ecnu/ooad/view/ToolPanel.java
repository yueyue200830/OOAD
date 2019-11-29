package com.ecnu.ooad.view;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiayi Zhu
 * @date 2019-11-16 21:06
 */
public class ToolPanel extends JPanel {
    private Controller controller;
    private Color backColor;

    /**
     * This is the panel for all tools.
     * @param controller Game controller.
     */
    public ToolPanel(Controller controller) {
        this.setSize(Constants.TOOL_WIDTH, Constants.TOOL_HEIGHT);
        this.setVisible(true);
        backColor = new Color(230, 230, 230);
        this.setBackground(backColor);
        this.controller = controller;
        this.initPanel();
    }

    /**
     * Initialize all small panels.
     */
    private void initPanel() {
        Font headlineFont = new Font("Arial", Font.BOLD, 26);

        Box box = Box.createVerticalBox();
        box.setBackground(backColor);

        JPanel labelPanel1 = new JPanel();
        JLabel labelIngredient = new JLabel("Ingredient Bar", JLabel.CENTER);
        labelIngredient.setFont(headlineFont);
        labelPanel1.add(labelIngredient);
        labelPanel1.setBackground(backColor);
        box.add(labelPanel1);
        box.add(new IngredientPanel(this.controller));

        JPanel labelPanel2 = new JPanel();
        JLabel labelOperation = new JLabel("Operation Bar");
        labelOperation.setFont(headlineFont);
        labelPanel2.add(labelOperation);
        labelPanel2.setBackground(backColor);
        box.add(labelPanel2);

        box.add(new OperationPanel(this.controller));

        JPanel labelPanel3 = new JPanel();
        JLabel labelMode = new JLabel("Mode Bar");
        labelMode.setFont(headlineFont);
        labelPanel3.add(labelMode);
        labelPanel3.setBackground(backColor);
        box.add(labelPanel3);
        JPanel panel5 = new JPanel();
        panel5.add(new JLabel(new ImageIcon("src/main/resources/margin.png")));
        box.add(panel5);
        box.add(new ModePanel(this.controller));

        this.add(box);

        JPanel panel4 = new JPanel();
        panel4.add(new JLabel(new ImageIcon("src/main/resources/icon.png")));
        box.add(panel4);
    }
}
