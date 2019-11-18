package com.ecnu.ooad.view;

import com.ecnu.ooad.Manager;
import com.ecnu.ooad.physics.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Yiqing Tao, Jiayi Zhu
 * @date 2019-11-17 12:51
 */
public class GamePanelMouseListener implements MouseListener {
    private Manager manager;

    public GamePanelMouseListener(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (MouseEvent.BUTTON1 == e.getButton() && !this.manager.isPlayMode()) {
            int condition = this.manager.getIngredientCondition();
            int[] position = this.getSquareMiddlePoint(e.getX(), e.getY());
            switch (condition) {
                case 0:
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

    public void addBall(int[] pos) {
        int x = pos[0];
        int y = pos[1];
        Ball newBall = new Ball(x, y);
        manager.addBall(newBall);
    }

    public void addSlope(int[] pos) {
        int x = pos[0];
        int y = pos[1];
        Slope slope = new Slope(x, y, 1, this.manager.getDirection());
        manager.addTool(slope);
    }

    public void addEmerald(int[] pos) {
        int x = pos[0];
        int y = pos[1];
        Emerald emerald = new Emerald(x, y);
        this.manager.addTool(emerald);
    }

    public void addDiamond(int[] pos) {
        int x = pos[0];
        int y = pos[1];
        Diamond diamond = new Diamond(x, y);
        System.out.println(diamond);
        this.manager.addTool(diamond);
    }

    public void addStraightTrack(int[] pos) {
        int x = pos[0];
        int y = pos[1];
        StraightTrack straightTrack = new StraightTrack(x, y, manager.getDirection());
        System.out.println(straightTrack);
        this.manager.addTool(straightTrack);
    }

    public void addHinderLeft(int[] pos) {
        int x = pos[0];
        int y = pos[1];
        HinderLeft hinderLeft = new HinderLeft(x, y);
        this.manager.addTool((Tool) hinderLeft);
    }

    public  void addHinderRight(int[] pos) {
        int x = pos[0];
        int y = pos[1];
        HinderRight hinderRight = new HinderRight(x, y);
        this.manager.addTool((Tool) hinderRight);
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
