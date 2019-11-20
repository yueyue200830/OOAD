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
                    this.addBall(position);
                    break;
                case 2:
                    break;
                case 3:
                    this.addSlope(position);
                    break;
                case 4:
                    this.addDiamond(position);
                    break;
                case 5:
                    this.addEmerald(position);
                    break;
                case 6:
                    this.addStraightTrack(position);
                    break;
                case 7:
                    this.addCurveTrack(position);
                    break;
                case 8:
                    this.addHinderLeft(position);
                    break;
                default:
                    this.addHinderRight(position);
                    break;
            }
        }
    }

    public int[] getSquareMiddlePoint(int x, int y) {
        int[] pos = new int[2];
        pos[0] = x / 20 * 20 + 10;
        pos[1] = y / 20 * 20 + 10;
        return pos;
    }

    public void selectObject(@NotNull int[] pos) {
        int x = pos[0] / 20;
        int y = pos[1] / 20;
        manager.selectObject(x, y);
    }

    // TODO Put new method into manager.
    public void addBall(@NotNull int[] pos) {
        int x = pos[0];
        int y = pos[1];
        Ball newBall = new Ball(x, y,1);
        manager.addBall(newBall, pos);
    }

    public void addSlope(@NotNull int[] pos) {
        int x = pos[0];
        int y = pos[1];
        Slope slope = new Slope(x, y, 1, this.manager.getDirection());
        manager.addTool(slope, pos);
    }

    public void addEmerald(@NotNull int[] pos) {
        int x = pos[0];
        int y = pos[1];
        Emerald emerald = new Emerald(x, y,1);
        this.manager.addTool(emerald, pos);
    }

    public void addDiamond(@NotNull int[] pos) {
        int x = pos[0];
        int y = pos[1];
        Diamond diamond = new Diamond(x, y,2);
        this.manager.addTool(diamond, pos);
    }

    public void addStraightTrack(@NotNull int[] pos) {
        int x = pos[0];
        int y = pos[1];
        StraightTrack straightTrack = new StraightTrack(x, y, manager.getDirection(), 1);
        this.manager.addTool(straightTrack, pos);
    }

    public void addCurveTrack(@NotNull int[] pos) {
        int x = pos[0];
        int y = pos[1];
        CurveTrack curveTrack = new CurveTrack(x, y, manager.getDirection());
        this.manager.addTool(curveTrack, pos);
    }

    public void addHinderLeft(@NotNull int[] pos) {
        int x = pos[0];
        int y = pos[1];
        HinderLeft hinderLeft = new HinderLeft(x, y,1);
        this.manager.addTool(hinderLeft, pos);
    }

    public  void addHinderRight(@NotNull int[] pos) {
        int x = pos[0];
        int y = pos[1];
        HinderRight hinderRight = new HinderRight(x, y,1);
        this.manager.addTool(hinderRight, pos);
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
