package com.ecnu.ooad.view;

import com.ecnu.ooad.Controller;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Yiqing Tao, Jiayi Zhu
 * @date 2019-11-17 12:51
 */
public class GamePanelMouseListener implements MouseListener {
    private Controller controller;

    /**
     * Constructor of Game Panel Mouse Listener.
     * @param controller controller of the game.
     */
    @Contract(pure = true)
    public GamePanelMouseListener(Controller controller) {
        this.controller = controller;
    }

    /**
     * When mouse get clicked, get what object mouse is clicked, then add corresponding object.
     * @param e Mouse Event
     */
    @Override
    public void mouseClicked(@NotNull MouseEvent e) {
        if (MouseEvent.BUTTON1 == e.getButton() && !this.controller.isPlayMode()) {
            int condition = this.controller.getIngredientCondition();
            int[] position = this.getSquareMiddlePoint(e.getX(), e.getY());
            switch (condition) {
                case 0:
                    this.selectObject(position);
                    break;
                case 1:
                    controller.addBall(position);
                    break;
                default:
                    controller.addTool(condition, position);
                    break;
            }
        }
    }

    /**
     * Get the center position of the mouse, in order to draw the object.
     * @param x The x position of mouse click.
     * @param y The y position of mouse click.
     * @return The center point of the grid
     */
    public int[] getSquareMiddlePoint(int x, int y) {
        int[] pos = new int[2];
        pos[0] = x / 20 * 20;
        pos[1] = y / 20 * 20;
        return pos;
    }

    /**
     * Select the object
     * @param pos The position of the object.
     */
    public void selectObject(@NotNull int[] pos) {
        int x = pos[0];
        int y = pos[1];
        controller.selectObject(x, y);
    }

    /**
     * When mouse get pressed.
     * @param e Mouse Event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mouse pressed");
    }

    /**
     * When mouse get released.
     * @param e Mouse evert.
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * When mouse get entered.
     * @param e Mouse event.
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * When mouse get exited.
     * @param e Mouse event.
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
