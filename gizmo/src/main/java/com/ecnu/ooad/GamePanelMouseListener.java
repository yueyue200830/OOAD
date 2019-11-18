package com.ecnu.ooad;

import com.ecnu.ooad.physics.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Yiqing Tao
 * @date 2019-11-17 12:51
 */
public class GamePanelMouseListener implements MouseListener {
    private Manager manager;
    private int angle;

    public GamePanelMouseListener(Manager manager) {
        this.manager = manager;
        this.angle = 0;
        System.out.println("new listener");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouse clicked");
        if (MouseEvent.BUTTON1 == e.getButton()) {
            int condition = this.manager.getIngredientCondition();
            System.out.println(condition);
            switch (condition) {
                case 0:
                    break;
                case 1:
                    this.addBall(e);
                    break;
                case 2:
                    break;
                case 3:
                    this.addSlope(e);
                    break;
                case 4:
                    this.addDiamond(e);
                    break;
                case 5:
                    this.addEmerald(e);
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                default:
                    break;
            }
        }
    }

    public void addBall(MouseEvent e) {
        System.out.println("add ball");
        int x = e.getX();
        int y = e.getY();
        Ball newBall = new Ball(x, y);
        manager.addBall(newBall);
    }

    public void addSlope(MouseEvent e) {
        System.out.println("add slop");
        int x = e.getX();
        int y = e.getY();
        Slope slope = new Slope(x, y, 1, this.manager.getAngle());
        manager.addTool(slope);
    }

    public void addEmerald(MouseEvent e) {
        System.out.println("add emerald");
        int x = e.getX();
        int y = e.getY();
        Emerald emerald = new Emerald(x, y);
        this.manager.addTool(emerald);
    }

    public void addDiamond(MouseEvent e) {
        System.out.println("add diamond");
        int x = e.getX();
        int y = e.getY();
        Diamond diamond = new Diamond(x, y);
        System.out.println(diamond);
        this.manager.addTool(diamond);
    }

    public void addStraightTrack(MouseEvent e) {
        System.out.println("add track");
        int x = e.getX();
        int y = e.getY();
        StraightTrack straightTrack = new StraightTrack(x,y);
        System.out.println(straightTrack);
        this.manager.addTool((Tool) straightTrack);
    }

    public void rotate() {
        this.angle = (this.angle + 1) % 4;
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
