package com.ecnu.ooad.physics;

import org.jbox2d.dynamics.Body;
import org.jetbrains.annotations.Contract;

import java.awt.*;

/**
 * @author Jiayi Zhu
 * @date 2019-11-22 13:30
 */
public class Substance {

    protected float positionX;
    protected float positionY;
    protected int direction;
    protected float scaleRate;
    protected Body[] bodies;
    protected int type;
    protected boolean isStatic;

    /**
     * This is a parent class of all used objects.
     * @param x Position x.
     * @param y Position y.
     * @param scaleRate Scale rate.
     */
    @Contract(pure = true)
    public Substance(float x, float y, float scaleRate) {
        this.positionX = x;
        this.positionY = y;
        this.scaleRate = scaleRate;
        this.direction = 0;
    }

    /**
     * Draw current object.
     * @param g Graphics tool.
     */
    public void drawMe(Graphics2D g) {

    }

    /**
     * Get scale rate.
     * @return Scale rate.
     */
    public float getScaleRate() {
        return scaleRate;
    }

    /**
     * Get position x.
     * @return Position x.
     */
    public float getPositionX() {
        return positionX;
    }

    /**
     * Get position y.
     * @return Position y.
     */
    public float getPositionY() {
        return positionY;
    }

    /**
     * Get the substance' direction.
     * @return
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Get the list of body.
     * @return Body list.
     */
    public Body[] getBodies() {
        return bodies;
    }

    /**
     * Get current substance's type.
     * @return Type.
     */
    public int getType() {
        return type;
    }
}
