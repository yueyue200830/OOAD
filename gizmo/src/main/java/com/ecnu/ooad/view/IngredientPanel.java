package com.ecnu.ooad.view;

import com.ecnu.ooad.Controller;

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
        this.setBackground(Color.blue);
        this.controller = controller;
        this.createIngredientBar();
        this.setLayout(new GridLayout(5, 2));
    }

    /**
     * Create all ingredients.
     */
    public void createIngredientBar() {
        JRadioButton btnMouse = new JRadioButton("mouse");
        JRadioButton btnBall = new JRadioButton("ball");
        JRadioButton btnAbsorber = new JRadioButton("absorber");
        JRadioButton btnSlope = new JRadioButton("slope");
        JRadioButton btnDiamond = new JRadioButton("diamond");
        JRadioButton btnEmerald = new JRadioButton("emerald");
        JRadioButton btnStraightTrack = new JRadioButton("straightTrack");
        JRadioButton btnCurveTrack = new JRadioButton("curveTrack");
        JRadioButton btnHinderLeft = new JRadioButton("hinderLeft");
        JRadioButton btnHinderRight = new JRadioButton("hinderRight");
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
