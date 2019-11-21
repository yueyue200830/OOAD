package com.ecnu.ooad.physics;

import java.awt.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-14 16:51
 */
public class HinderRight extends Hinder {

    public HinderRight(float x, float y, float scaleRate) {
        super(x, y, scaleRate);
        this.type = 9;
    }

    @Override
    public void drawMe(Graphics2D g) {
        super.drawMe(g);
    }
}
