package com.ecnu.ooad.view;

import com.ecnu.ooad.Controller;
import com.ecnu.ooad.utils.IngredientCondition;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiayi Zhu
 * @date 2019-11-16 21:09
 */
public class OperationPanel extends JPanel {
    private Controller controller;
    private static String pathRotate = OperationPanel.class.getClassLoader().getResource("rotate.png").toString();
    private static String pathDelete = OperationPanel.class.getClassLoader().getResource("delete.png").toString();
    private static String pathZoomIn = OperationPanel.class.getClassLoader().getResource("zoomIn.png").toString();
    private static String pathZoomOut = OperationPanel.class.getClassLoader().getResource("zoomOut.png").toString();
    public static String imgRotate = "<html><img src='" + pathRotate + "' width='40' height='40'/></html>";
    public static String imgDelete = "<html><img src='" + pathDelete + "' width='40' height='40'/></html>";
    public static String imgZoomIn = "<html><img src='" + pathZoomIn + "' width='40' height='40'/></html>";
    public static String imgZoomOut = "<html><img src='" + pathZoomOut + "' width='40' height='40'/></html>";

    /**
     * This is the panel for some operation button.
     * @param controller Game controller.
     */
    public OperationPanel(Controller controller) {
        this.controller = controller;
        this.setSize(200, 200);
        this.setVisible(true);
        this.setBackground(Color.LIGHT_GRAY);
        this.createButtons();
        this.setLayout(new GridLayout(2, 2));
    }

    /**
     * Add operation button in the panel.
     */
    private void createButtons() {
        JButton btnRotate = new JButton(imgRotate);
        JButton btnDelete = new JButton(imgDelete);
        JButton btnZoomIn = new JButton(imgZoomIn);
        JButton btnZoomOut = new JButton(imgZoomOut);
        btnRotate.addActionListener(new OperationActionListener(this.controller));
        btnDelete.addActionListener(new OperationActionListener(this.controller));
        btnZoomIn.addActionListener(new OperationActionListener(this.controller));
        btnZoomOut.addActionListener(new OperationActionListener(this.controller));
        this.add(btnRotate);
        this.add(btnDelete);
        this.add(btnZoomIn);
        this.add(btnZoomOut);
    }
}
