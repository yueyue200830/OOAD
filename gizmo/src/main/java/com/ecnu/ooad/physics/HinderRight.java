package com.ecnu.ooad.physics;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-14 16:51
 */
public class HinderRight extends Hinder {

    /**
     * The constructor of hinderRight.
     * @param x the x axis position of hinderRight.
     * @param y the y axis position of hinderRight.
     */
    public HinderRight(float x, float y) {
        super(x, y);
        this.type = 9;
    }

    /**
     * Draw the hinderRight on board.
     * @param g Graphics tool.
     * @param panel Game panel.
     */
    @Override
    public void drawMe(Graphics2D g, JPanel panel) {
        super.drawMe(g, panel);
    }
}
