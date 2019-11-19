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
    private Color color;
    private float width;
    private float height;

    public Hinder(float x, float y) {
        super(x, y);
        this.color = Color.LIGHT_GRAY;
        this.width = Constants.HINDER_WIDTH;
        this.height = Constants.HINDER_HEIGHT;
        this.bodies = new Body[1];
        this.initHinder(x, y);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    private void initHinder(float x, float y) {
        float leftCornerX = x;
        float leftCornerY = y + Constants.GRID_LENGTH / 2 - Constants.HINDER_HEIGHT / 2;
        this.bodies[0] = BodyUtil.initRectangle(leftCornerX, leftCornerY, this.width, this.height);
    }

    @Override
    public void drawMe(@NotNull Graphics2D g) {
        g.setColor(this.color);
        int x = (int) this.bodies[0].getPosition().x;
        int y = (int) this.bodies[0].getPosition().y;
        g.fillRect(x - Constants.HINDER_WIDTH / 2, y - Constants.HINDER_HEIGHT / 2, Constants.HINDER_WIDTH, Constants.HINDER_HEIGHT);
    }
}
