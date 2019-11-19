package com.ecnu.ooad.physics;

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

    @Contract(pure = true)
    public Tool(float x, float y) {
        this.positionX = x;
        this.positionY = y;
    }
    public void drawMe(Graphics2D g) {

    }

}
