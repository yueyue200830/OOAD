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
public class Emerald extends Obstacle {

    private float height;
    private float width;
    private Color color;

    public Emerald(float x, float y) {
        super(x, y);
        this.height = Constants.EMERALD_HEIGHT;
        this.width = Constants.EMERALD_WIDTH;
        this.initEmerald(x, y);
        this.color = Color.green;
    }

    private void initEmerald(float worldX, float worldY) {
        this.body = BodyUtil.initRectangle(worldX, worldY, this.width, this.height);
    }

    public Body getBody() {
        return body;
    }

    public float getInnerX() {
        return this.body.getPosition().x - this.width / 2;
    }

    public float getInnerY() {
        return this.body.getPosition().y - this.height / 2;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    @Override
    public void drawMe(@NotNull Graphics2D g) {
        g.setColor(this.color);
        g.fillRect((int) getInnerX(), (int) getInnerY(), (int) this.width, (int) this.height);
    }
}
