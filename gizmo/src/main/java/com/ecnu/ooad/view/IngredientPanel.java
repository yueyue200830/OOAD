package com.ecnu.ooad.view;

import com.ecnu.ooad.IngredientActionListener;
import com.ecnu.ooad.Manager;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiayi Zhu
 * @date 2019-11-16 21:08
 */
public class IngredientPanel extends JPanel {

    private Manager manager;

    public IngredientPanel(Manager manager) {
        this.setSize(200, 200);
        this.setVisible(true);
        this.setBackground(Color.blue);
        this.manager = manager;
        this.createIngredientBar();
        this.setLayout(new GridLayout(5, 2));
       // System.out.println("new ingredient " + this.manager);
    }

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
        btnMouse.addActionListener(new IngredientActionListener(this.manager));
        btnBall.addActionListener(new IngredientActionListener(this.manager));
        btnAbsorber.addActionListener(new IngredientActionListener(this.manager));
        btnSlope.addActionListener(new IngredientActionListener(this.manager));
        btnDiamond.addActionListener(new IngredientActionListener(this.manager));
        btnEmerald.addActionListener(new IngredientActionListener(this.manager));
        btnStraightTrack.addActionListener(new IngredientActionListener(this.manager));
        btnCurveTrack.addActionListener(new IngredientActionListener(this.manager));
        btnHinderLeft.addActionListener(new IngredientActionListener(this.manager));
        btnHinderRight.addActionListener(new IngredientActionListener(this.manager));
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
        this.add(btnBall);
        this.add(btnMouse);
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
