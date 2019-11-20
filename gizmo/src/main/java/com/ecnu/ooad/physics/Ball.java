package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.utils.BodyUtil;
import org.jbox2d.dynamics.Body;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Jiayi Zhu, Yiqing Tao
 * @date 2019-11-16 20:34
 */
public class Ball {
    private float radius;
    private Body body;
    private Color color = Color.cyan;
    private float positionX;
    private float positionY;
    private float scaleRate;

    public Ball(float worldX, float worldY, float scaleRate) {
        this.positionX = worldX + 2;
        this.positionY = worldY + 2;
        this.scaleRate = scaleRate;
        this.radius = Constants.CIRCLE_RADIUS * scaleRate;
        this.initBallInWorld();
    }

    private void initBallInWorld() {
        float x = positionX + radius;
        float y = positionY + radius;
        this.body = BodyUtil.initCircle(x, y, radius, false);
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public float getRadius() {
        return radius;
    }

    public Body getBody() {
        return body;
    }

    public float getScaleRate() {
        return scaleRate;
    }

    public void drawMe(@NotNull Graphics2D g) {
        int x = (int) (body.getPosition().x - radius);
        int y = (int) (body.getPosition().y - radius);
        int delimiter = (int) (radius * 2);

        g.setColor(color);
        g.fillOval(x, y, delimiter, delimiter);
    }
}
