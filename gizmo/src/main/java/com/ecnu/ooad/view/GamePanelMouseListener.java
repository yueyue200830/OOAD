package com.ecnu.ooad.view;

import com.ecnu.ooad.Controller;
import com.ecnu.ooad.Manager;
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

    @Contract(pure = true)
    public GamePanelMouseListener(Controller controller) {
        this.controller = controller;
    }

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

    public int[] getSquareMiddlePoint(int x, int y) {
        int[] pos = new int[2];
        pos[0] = x / 20 * 20;
        pos[1] = y / 20 * 20;
        return pos;
    }

    public void selectObject(@NotNull int[] pos) {
        int x = pos[0];
        int y = pos[1];
        controller.selectObject(x, y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mouse pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
