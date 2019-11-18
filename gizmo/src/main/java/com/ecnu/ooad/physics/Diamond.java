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
 * @author Yiqing Tao
 * @date 2019-11-16 10:56
 */
public class Diamond extends Obstacle {
    private Body diamond;
    private float radius;
    private Color color;

    public Diamond(float x, float y) {
        this.radius = 10f;
        this.color = Color.white;
        this.initDiamond(x,y);
    }

    public void initDiamond(float worldX, float worldY) {
        BodyDef bd = new BodyDef();
        bd.type = BodyType.STATIC;
        bd.position = new Vec2(worldX, worldY);
        FixtureDef fd = new FixtureDef();
        CircleShape cs = new CircleShape();
        cs.m_radius = this.radius;
        fd.shape = cs;
        fd.density = 0.5f;
        fd.friction = 0.3f;
        fd.restitution = 0.6f;

        this.diamond = Manager.world.createBody(bd);
        this.diamond.createFixture(fd);

    }
    public float getPixelX() {
        return diamond.getPosition().x - this.radius;
    }

    public float getPixelY() {
        return diamond.getPosition().y - this.radius;
    }

    public float getWidthOrHeight() {
        return this.radius * 2;
    }

    @Override
    public void drawMe(Graphics2D g) {
        g.setColor(this.color);
        g.fillOval((int) this.getPixelX(), (int) this.getPixelY(), (int) this.getWidthOrHeight(), (int) this.getWidthOrHeight());
    }
}
