package com.ecnu.ooad.physics;

import com.ecnu.ooad.Manager;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.awt.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-14 16:51
 */
public class Emerald extends Obstacle {
    private Body body;
    private float height;
    private float width;
    private Color color;

    public Emerald(float worldX, float worldY) {
        this.height = 20;
        this.width = 20;
        this.initEmerald(worldX, worldY, this.width, this.height);
    }

    private void initEmerald(float worldX, float worldY, float width, float height) {
        this.height = height;
        this.width = width;
        this.color = Color.green;

        BodyDef bd = new BodyDef();
        bd.position = new Vec2(worldX, worldY);
        bd.type = BodyType.STATIC;
        FixtureDef fd = new FixtureDef();
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(this.width / 2, this.height / 2);
        fd.shape = ps;

        this.body = Manager.world.createBody(bd);
        this.body.createFixture(fd);
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

    @Override
    public void drawMe(Graphics2D g) {
        g.setColor(this.color);
        g.fillRect((int) getInnerX(), (int) getInnerY(), (int) this.width, (int) this.height);
    }
}
