package com.ecnu.ooad.view;

import com.ecnu.ooad.Manager;
import com.ecnu.ooad.physics.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Yiqing Tao, Jiayi Zhu
 * @date 2019-11-17 12:51
 */
public class GamePanelMouseListener implements MouseListener {
    private Manager manager;

    @Contract(pure = true)
    public GamePanelMouseListener(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void mouseClicked(@NotNull MouseEvent e) {
        if (MouseEvent.BUTTON1 == e.getButton() && !this.manager.isPlayMode()) {
            int condition = this.manager.getIngredientCondition();
            int[] position = this.getSquareMiddlePoint(e.getX(), e.getY());
            switch (condition) {
                case 0:
                    this.selectObject(position);
                    break;
                case 1:
                    manager.addBall(position);
                    break;
                default:
                    manager.addTool(condition, position);
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
        int x = pos[0] / 20;
        int y = pos[1] / 20;
        manager.selectObject(x, y);
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
