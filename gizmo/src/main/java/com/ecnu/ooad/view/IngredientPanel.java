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
        String pathMouse = IngredientCondition.class.getClassLoader().getResource("mouse.png").toString();
        String pathBall = IngredientCondition.class.getClassLoader().getResource("ball.png").toString();
        String pathHole = IngredientCondition.class.getClassLoader().getResource("hole.png").toString();
        String pathSlope = IngredientCondition.class.getClassLoader().getResource("triangle.png").toString();
        String pathDiamond = IngredientCondition.class.getClassLoader().getResource("circle.png").toString();
        String pathEmerald = IngredientCondition.class.getClassLoader().getResource("square.png").toString();
        String pathStraightTrack = IngredientCondition.class.getClassLoader().getResource("straightTrack.png").toString();
        String pathCurveTrack = IngredientCondition.class.getClassLoader().getResource("curveTrack.png").toString();
        String pathHinder = IngredientCondition.class.getClassLoader().getResource("hinder.png").toString();
        String mouse = "<html><table><tr><td>mouse</td><td><img src='"+pathMouse+"' width='20' height='20'/></td></tr></html>";
        String ball = "<html><table><tr><td>ball</td><td><img src='"+pathBall+"' width='20' height='20'/></td></tr></html>";
        String hole = "<html><table><tr><td>absorber</td><td><img src='"+pathHole+"' width='20' height='20'/></td></tr></html>";
        String slope = "<html><table><tr><td>triangle</td><td><img src='"+pathSlope+"' width='20' height='20'/></td></tr></html>";
        String diamond = "<html><table><tr><td>circle</td><td><img src='"+pathDiamond+"' width='20' height='20'/></td></tr></html>";
        String emerald = "<html><table><tr><td>square</td><td><img src='"+pathEmerald+"' width='20' height='20'/></td></tr></html>";
        String straightTrack = "<html><table><tr><td>straight track</td><td><img src='"+pathStraightTrack+"' width='20' height='20'/></td></tr></html>";
        String curveTrack = "<html><table><tr><td>curve track</td><td><img src='"+pathCurveTrack+"' width='20' height='20'/></td></tr></html>";
        String hinderLeft = "<html><table><tr><td>left hinder</td><td><img src='"+pathHinder+"' width='20' height='20'/></td></tr></html>";
        String hinderRight = "<html><table><tr><td>right hinder</td><td><img src='"+pathHinder+"' width='20' height='20'/></td></tr></html>";

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
