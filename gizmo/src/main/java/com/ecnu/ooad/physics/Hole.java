package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import org.jbox2d.dynamics.Body;

import java.awt.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-14 16:51
 */
public class Hole extends Tool {

    private Color color;
    private int length;
    private float centerX;
    private  float centerY;

    /**
     * Constructor of Hole.
     * @param x the x axis of left corner of the hole.
     * @param y the y axis of left corner of the hole.
     * @param scaleRate the scale rate of hole.
     */
    public Hole(float x, float y, float scaleRate) {
        super(x, y,1);
        this.type = 2;
        this.color = Color.ORANGE;
        this.length = Constants.GRID_LENGTH;
        this.centerX = this.positionX + this.length / 2;
        this.centerY = this.positionY + this.length / 2;
        this.bodies = new Body[0];
    }

    /**
     * judge whether the ball is falling into the absorber.
     * @param ballX  the X axis center position of ball.
     * @param ballY  ths y axis center position of ball.
     * @return true if falling.
     */
    public boolean attach(float ballX, float ballY) {
        float distanceX = Math.abs(ballX - this.centerX);
        float distanceY = Math.abs(ballY - this.centerY);
        return distanceX <= 2 && distanceY <= 2;
    }

    /**
     * Draw absorber on board.
     * @param g Graphics tool.
     */
    @Override
    public void drawMe(Graphics2D g) {
        g.setColor(this.color);
        int width = this.length;
        int height = this.length;
        int arcAngle = 360;
        int startAngle  =  0;
        g.drawArc((int) this.positionX, (int) this.positionY, width, height, startAngle, arcAngle);

    }
}
