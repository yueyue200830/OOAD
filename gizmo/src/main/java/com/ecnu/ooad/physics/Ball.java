package com.ecnu.ooad.physics;

import com.ecnu.ooad.Manager;
import com.ecnu.ooad.Utils.BodyUtil;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.awt.*;

/**
 * @author Jiayi Zhu
 * @date 2019-11-16 20:34
 */
public class Ball {
    private float radius;
    public Body body;
    private Color color = Color.cyan;
    private float positionX;
    private float positionY;

    public Ball(float worldX, float worldY) {
        this.positionX = worldX;
        this.positionY = worldY;
        this.radius = 8f;
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

    public void drawMe(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) this.getInnerX(), (int) this.getInnerY(), (int) this.getDelimiter(), (int) this.getDelimiter());
    }
}
