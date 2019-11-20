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

    public Hinder(float x, float y, float scaleRate) {
        super(x, y, scaleRate);
        this.color = Color.LIGHT_GRAY;
        this.width = Constants.HINDER_WIDTH;
        this.height = Constants.HINDER_HEIGHT;
        this.bodies = new Body[1];
        this.initHinder();
    }

    private void initHinder() {
        float leftCornerX = this.positionX + this.width / 2;
        float leftCornerY = this.positionY + Constants.GRID_LENGTH - this.height / 2;
        this.bodies[0] = BodyUtil.initRectangle(leftCornerX, leftCornerY, this.width, this.height);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public void drawMe(@NotNull Graphics2D g) {
        int x = (int) (this.bodies[0].getPosition().x - this.width / 2);
        int y = (int) (this.bodies[0].getPosition().y - this.height / 2);

        g.setColor(this.color);
        g.fillRect(x, y, (int) this.width, (int) this.height);
    }
}
