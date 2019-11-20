package com.ecnu.ooad.physics;

import org.jbox2d.dynamics.Body;
import org.jetbrains.annotations.Contract;

import java.awt.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-14 16:51
 */
public class Tool {
    protected float positionX;
    protected float positionY;
    protected int direction;
    protected float scaleRate;
    protected Body[] bodies;

    @Contract(pure = true)
    public Tool(float x, float y, float scaleRate) {
        this.positionX = x;
        this.positionY = y;
        this.scaleRate = scaleRate;
        this.direction = 0;
    }

    public void drawMe(Graphics2D g) {

    }

    public float getScaleRate() {
        return scaleRate;
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public int getDirection() {
        return direction;
    }

    public Body[] getBodies() {
        return bodies;
    }
}
