package com.ecnu.ooad.view;

import com.ecnu.ooad.Controller;
import com.ecnu.ooad.utils.IngredientCondition;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Yiqing Tao
 * @date 2019-11-17 12:51
 */
public class IngredientActionListener implements ActionListener {
    private Controller controller;

    /**
     * Constructor of ingredient panel action listener.
     * @param controller Controller of the game.
     */
    @Contract(pure = true)
    public IngredientActionListener(Controller controller) {
        this.controller = controller;
    }

    /**
     * Deal with when the mouse is clicked.
     * @param e Action event.
     */
    @Override
    public void actionPerformed(@NotNull ActionEvent e) {
        String pathMouse = IngredientCondition.class.getClassLoader().getResource("mouse.png").toString();
        String pathBall = IngredientCondition.class.getClassLoader().getResource("ball.png").toString();
        String pathHole = IngredientCondition.class.getClassLoader().getResource("hole.png").toString();
        String pathSlope = IngredientCondition.class.getClassLoader().getResource("triangle.png").toString();
        String pathDiamond = IngredientCondition.class.getClassLoader().getResource("circle.png").toString();
        String pathEmerald = IngredientCondition.class.getClassLoader().getResource("square.png").toString();
        String pathStraightTrack = IngredientCondition.class.getClassLoader().getResource("straightTrack.png").toString();
        String pathCurveTrack = IngredientCondition.class.getClassLoader().getResource("curveTrack.png").toString();
        String pathHinder = IngredientCondition.class.getClassLoader().getResource("hinder.png").toString();
        String mouse = "<html><table><tr><td width='90'>mouse</td><td><img src='"+pathMouse+"' width='20' height='20'/></td></tr></html>";
        String ball = "<html><table><tr><td width='90'>ball</td><td><img src='"+pathBall+"' width='20' height='20'/></td></tr></html>";
        String hole = "<html><table><tr><td width='90'>absorber</td><td><img src='"+pathHole+"' width='20' height='20'/></td></tr></html>";
        String slope = "<html><table><tr><td width='90'>triangle</td><td><img src='"+pathSlope+"' width='20' height='20'/></td></tr></html>";
        String diamond = "<html><table><tr><td width='90'>circle</td><td><img src='"+pathDiamond+"' width='20' height='20'/></td></tr></html>";
        String emerald = "<html><table><tr><td width='90'>square</td><td><img src='"+pathEmerald+"' width='20' height='20'/></td></tr></html>";
        String straightTrack = "<html><table><tr><td width='90'>straight track</td><td><img src='"+pathStraightTrack+"' width='20' height='20'/></td></tr></html>";
        String curveTrack = "<html><table><tr><td width='90'>curve track</td><td><img src='"+pathCurveTrack+"' width='20' height='20'/></td></tr></html>";
        String hinderLeft = "<html><table><tr><td width='90'>left hinder</td><td><img src='"+pathHinder+"' width='20' height='20'/></td></tr></html>";
        String hinderRight = "<html><table><tr><td width='90'>right hinder</td><td><img src='"+pathHinder+"' width='20' height='20'/></td></tr></html>";

        String command = e.getActionCommand();
        if (mouse.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.Mouse.getValue());
        } else if (ball.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.Ball.getValue());
        } else if (hole.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.Absorber.getValue());
        } else if (slope.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.Slope.getValue());
        } else if (diamond.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.Diamond.getValue());
        } else if (emerald.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.Emerald.getValue());
        } else if (straightTrack.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.StraightTrack.getValue());
        } else if (curveTrack.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.CurveTrack.getValue());
        } else if (hinderLeft.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.HinderLeft.getValue());
        } else if (hinderRight.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.HinderRight.getValue());
        }
    }
}
