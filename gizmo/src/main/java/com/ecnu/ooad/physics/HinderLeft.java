package com.ecnu.ooad.physics;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-14 16:51
 */
public class HinderLeft extends Hinder {

    /**
     * The constructor of hinderLeft.
     * @param x the x axis position of hinderLeft.
     * @param y the y axis position of hinderLeft.
     */
    public HinderLeft(float x, float y) {
        super(x, y);
        this.type = 8;
    }

    /**
     * Draw the hinderLeft on board.
     * @param g Graphics tool.
     * @param panel Game panel.
     */
    @Override
    public void drawMe(@NotNull Graphics2D g, JPanel panel) {
        super.drawMe(g, panel);
    }
}
