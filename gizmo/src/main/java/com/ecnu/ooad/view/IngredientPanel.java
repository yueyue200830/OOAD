package com.ecnu.ooad.view;

import com.ecnu.ooad.Controller;
import com.ecnu.ooad.utils.IngredientCondition;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiayi Zhu, Yiqing Tao
 * @date 2019-11-16 21:08
 */
public class IngredientPanel extends JPanel {

    private Controller controller;

    private static String pathMouse = IngredientCondition.class.getClassLoader().getResource("mouse.png").toString();
    private static String pathBall = IngredientCondition.class.getClassLoader().getResource("ball.png").toString();
    private static String pathHole = IngredientCondition.class.getClassLoader().getResource("hole.png").toString();
    private static String pathSlope = IngredientCondition.class.getClassLoader().getResource("triangle.png").toString();
    private static String pathDiamond = IngredientCondition.class.getClassLoader().getResource("circle.png").toString();
    private static String pathEmerald = IngredientCondition.class.getClassLoader().getResource("square.png").toString();
    private static String pathStraightTrack = IngredientCondition.class.getClassLoader().getResource("straightTrack.png").toString();
    private static String pathCurveTrack = IngredientCondition.class.getClassLoader().getResource("curveTrack.png").toString();
    private static String pathHinder = IngredientCondition.class.getClassLoader().getResource("hinder.png").toString();
    public static String mouse = "<html><table><tr><td><img src='"+pathMouse+"' width='20' height='20'/></td><td>mouse</td></tr></html>";
    public static String ball = "<html><table><tr><td><img src='"+pathBall+"' width='20' height='20'/></td><td>ball</td></tr></html>";
    public static String hole = "<html><table><tr><td><img src='"+pathHole+"' width='20' height='20'/></td><td>absorber</td></tr></html>";
    public static String slope = "<html><table><tr><td><img src='"+pathSlope+"' width='20' height='20'/></td><td>triangle</td></tr></html>";
    public static String diamond = "<html><table><tr><td><img src='"+pathDiamond+"' width='20' height='20'/></td><td>circle</td></tr></html>";
    public static String emerald = "<html><table><tr><td><img src='"+pathEmerald+"' width='20' height='20'/></td><td>square</td></tr></html>";
    public static String straightTrack = "<html><table><tr><td><img src='"+pathStraightTrack+"' width='20' height='20'/></td><td>straight track</td></tr></html>";
    public static String curveTrack = "<html><table><tr><td><img src='"+pathCurveTrack+"' width='20' height='20'/></td><td>curve track</td></tr></html>";
    public static String hinderLeft = "<html><table><tr><td><img src='"+pathHinder+"' width='20' height='20'/></td><td>left hinder</td></tr></html>";
    public static String hinderRight = "<html><table><tr><td><img src='"+pathHinder+"' width='20' height='20'/></td><td>right hinder</td></tr></html>";


    /**
     * This is the panel contains all physics button.
     * @param controller Controller of the game.
     */
    public IngredientPanel(Controller controller) {
        this.setSize(200, 200);
        this.setVisible(true);
        this.setBackground(Color.LIGHT_GRAY);
        this.controller = controller;
        this.createIngredientBar();
        this.setLayout(new GridLayout(5, 2));
    }

    /**
     * Create all ingredients.
     */
    public void createIngredientBar() {
        JRadioButton btnMouse = new JRadioButton(mouse);
        JRadioButton btnBall = new JRadioButton(ball);
        JRadioButton btnAbsorber = new JRadioButton(hole);
        JRadioButton btnSlope = new JRadioButton(slope);
        JRadioButton btnDiamond = new JRadioButton(diamond);
        JRadioButton btnEmerald = new JRadioButton(emerald);
        JRadioButton btnStraightTrack = new JRadioButton(straightTrack);
        JRadioButton btnCurveTrack = new JRadioButton(curveTrack);
        JRadioButton btnHinderLeft = new JRadioButton(hinderLeft);
        JRadioButton btnHinderRight = new JRadioButton(hinderRight);
        ButtonGroup bg = new ButtonGroup();
        Font font = new Font("Arial", Font.PLAIN, 14);
        btnMouse.setFont(font);
        btnBall.setFont(font);
        btnAbsorber.setFont(font);
        btnSlope.setFont(font);
        btnDiamond.setFont(font);
        btnEmerald.setFont(font);
        btnStraightTrack.setFont(font);
        btnCurveTrack.setFont(font);
        btnHinderLeft.setFont(font);
        btnHinderRight.setFont(font);

        btnMouse.addActionListener(new IngredientActionListener(this.controller));
        btnBall.addActionListener(new IngredientActionListener(this.controller));
        btnAbsorber.addActionListener(new IngredientActionListener(this.controller));
        btnSlope.addActionListener(new IngredientActionListener(this.controller));
        btnDiamond.addActionListener(new IngredientActionListener(this.controller));
        btnEmerald.addActionListener(new IngredientActionListener(this.controller));
        btnStraightTrack.addActionListener(new IngredientActionListener(this.controller));
        btnCurveTrack.addActionListener(new IngredientActionListener(this.controller));
        btnHinderLeft.addActionListener(new IngredientActionListener(this.controller));
        btnHinderRight.addActionListener(new IngredientActionListener(this.controller));
        bg.add(btnBall);
        bg.add(btnMouse);
        bg.add(btnAbsorber);
        bg.add(btnSlope);
        bg.add(btnDiamond);
        bg.add(btnEmerald);
        bg.add(btnStraightTrack);
        bg.add(btnCurveTrack);
        bg.add(btnHinderLeft);
        bg.add(btnHinderRight);
        this.add(btnMouse);
        this.add(btnBall);
        this.add(btnAbsorber);
        this.add(btnSlope);
        this.add(btnDiamond);
        this.add(btnEmerald);
        this.add(btnStraightTrack);
        this.add(btnCurveTrack);
        this.add(btnHinderLeft);
        this.add(btnHinderRight);
    }
}
