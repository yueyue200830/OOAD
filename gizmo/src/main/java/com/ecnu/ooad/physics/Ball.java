package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.utils.BodyUtil;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
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
        this.positionX = worldX;
        this.positionY = worldY;
        this.scaleRate = scaleRate;
        this.radius = Constants.CIRCLE_RADIUS;
        this.initBallInWorld(this.positionX, this.positionY);
    }

    private void initBallInWorld(float worldX, float worldY) {

        this.body = BodyUtil.initCircle(worldX, worldY, this.radius, false);
    }

    public float getInnerX() {
        return body.getPosition().x - radius;
    }

    public float getInnerY() {
        return body.getPosition().y - radius;
    }

    public float getDelimiter() {
        return radius * 2;
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
        g.setColor(color);
        g.fillOval((int) this.getInnerX(), (int) this.getInnerY(), (int) this.getDelimiter(), (int) this.getDelimiter());
    }
}
