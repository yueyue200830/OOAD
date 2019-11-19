package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.utils.BodyUtil;
import org.jbox2d.dynamics.Body;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-14 16:51
 */
public class Hinder extends Tool {
    private Body body;
    private Color color;

    public Hinder(float x, float y) {
        super(x, y);
        this.color = Color.LIGHT_GRAY;
        this.initHinder(x, y);
    }

    private void initHinder(float x, float y) {
        float leftCornerX = x;
        float leftCornerY = y + Constants.GRID_LENGTH / 2 - Constants.HINDER_HEIGHT / 2;
        this.body = BodyUtil.initRectangle(leftCornerX, leftCornerY, Constants.HINDER_WIDTH, Constants.HINDER_HEIGHT);


    }
    @Override
    public void drawMe(@NotNull Graphics2D g) {
        g.setColor(this.color);
        int x = (int) this.body.getPosition().x;
        int y = (int) this.body.getPosition().y;
        g.fillRect(x - Constants.HINDER_WIDTH / 2, y - Constants.HINDER_HEIGHT / 2, Constants.HINDER_WIDTH, Constants.HINDER_HEIGHT);
    }
}
