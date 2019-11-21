package com.ecnu.ooad.physics;

import java.awt.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-14 16:51
 */
public class Hole extends Tool {

    public Hole(float x, float y) {
        super(x, y,1);
        this.type = 2;
    }

    @Override
    public void drawMe(Graphics2D g) {

    }
}
