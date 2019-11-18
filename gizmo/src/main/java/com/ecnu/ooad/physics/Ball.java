package com.ecnu.ooad.physics;

import com.ecnu.ooad.Manager;
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

    public Ball(float worldX, float worldY) {
        this.radius = 10f;
        initBallInWorld(worldX, worldY);
    }

    private void initBallInWorld(float worldX, float worldY) {
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(worldX, worldY);
        bd.type = BodyType.DYNAMIC;

        FixtureDef fd = new FixtureDef();
        CircleShape cs = new CircleShape();
        cs.m_radius = radius;
        fd.shape = cs;
        fd.density = 0.5f;
        fd.friction = 0.3f;
        fd.restitution = 0.6f;

        this.body = Manager.world.createBody(bd);
        this.body.createFixture(fd);
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
