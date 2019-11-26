package com.ecnu.ooad.physics;

import org.jbox2d.dynamics.Body;

import java.awt.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-14 16:51
 */
public class Obstacle extends Tool {

    /**
     * The father class of all obstacles, including diamond, emerald, slope.
     * @param x The x axis position of obstacle.
     * @param y The y axis position of obstacls.
     * @param scaleRate The scaling rate.
     */
    public Obstacle(float x, float y, float scaleRate) {
        super(x, y, scaleRate);
        this.bodies = new Body[1];
    }

    @Override
    public void drawMe(Graphics2D g){

    }
}
