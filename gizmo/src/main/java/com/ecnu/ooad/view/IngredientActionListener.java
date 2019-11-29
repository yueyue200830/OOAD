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
        String command = e.getActionCommand();

        if (IngredientPanel.mouse.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.Mouse.getValue());
        } else if (IngredientPanel.ball.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.Ball.getValue());
        } else if (IngredientPanel.hole.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.Absorber.getValue());
        } else if (IngredientPanel.slope.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.Slope.getValue());
        } else if (IngredientPanel.diamond.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.Diamond.getValue());
        } else if (IngredientPanel.emerald.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.Emerald.getValue());
        } else if (IngredientPanel.straightTrack.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.StraightTrack.getValue());
        } else if (IngredientPanel.curveTrack.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.CurveTrack.getValue());
        } else if (IngredientPanel.hinderLeft.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.HinderLeft.getValue());
        } else if (IngredientPanel.hinderRight.equals(command)) {
            this.controller.setIngredientCondition(IngredientCondition.HinderRight.getValue());
        }
    }
}
