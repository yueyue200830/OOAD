package com.ecnu.ooad.view;

import com.ecnu.ooad.Controller;

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
    public static String imgRotate = "<html><img src='" + pathRotate + "' width='30' height='30'/></html>";
    public static String imgDelete = "<html><img src='" + pathDelete + "' width='30' height='30'/></html>";
    public static String imgZoomIn = "<html><img src='" + pathZoomIn + "' width='30' height='30'/></html>";
    public static String imgZoomOut = "<html><img src='" + pathZoomOut + "' width='30' height='30'/></html>";

    /**
     * This is the panel for some operation button.
     * @param controller Game controller.
     */
    public OperationPanel(Controller controller) {
        this.controller = controller;
        this.setSize(200, 200);
        this.setVisible(true);
        this.createButtons();
        this.setLayout(new GridLayout(1, 4));
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
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        p1.add(btnRotate);
        p2.add(btnDelete);
        p3.add(btnZoomIn);
        p4.add(btnZoomOut);
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
    }
}
