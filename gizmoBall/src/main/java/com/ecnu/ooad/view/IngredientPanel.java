package com.ecnu.ooad.view;

import com.ecnu.ooad.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-16 12:15
 */
public class IngredientPanel extends JPanel {
    public IngredientPanel() {
        this.setSize(200,200);
        this.setVisible(true);
        this.setBackground(Color.blue);
        this.createIngredientBar();
        this.setLayout(new GridLayout(5, 2));

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
