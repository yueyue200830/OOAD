package com.ecnu.ooad.physics;

import java.awt.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-14 16:51
 */
public abstract class Obstacle implements Tool {
    @Override
    public abstract void drawMe(Graphics2D g);
}
