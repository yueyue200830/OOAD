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
 * @author Yiqing Tao, Jiayi Zhu
 * @date 2019-11-14 16:51
 */
public class Slope extends Obstacle {
    private float worldX;
    private float worldY;
    private float edge;
    private Body body;
    private Color color;
    private int angle;

    public Slope(float worldX, float worldY) {
        this(worldX, worldY, 1, 0);
    }

    public Slope(float worldX, float worldY, float scaleRate, int angle) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.angle = angle;
        this.color = Color.WHITE;
        this.edge = 20;
        initSlope(worldX, worldY);
    }

    private void initSlope(float worldX, float worldY) {
        BodyDef bd = new BodyDef();
        bd.position.set(new Vec2(worldX - edge / 2, worldY - edge / 2));
        bd.bullet = true;
        bd.type = BodyType.STATIC;

        PolygonShape ps = new PolygonShape();
        switch (angle) {
            case 0:
                ps.set(new Vec2[]{new Vec2(0, 0), new Vec2(edge, 0), new Vec2(0, edge)}, 3);
                break;
            case 1:
                ps.set(new Vec2[]{new Vec2(0, 0), new Vec2(edge, 0), new Vec2(edge, edge)}, 3);
                break;
            case 2:
                ps.set(new Vec2[]{new Vec2(edge, 0), new Vec2(edge, edge), new Vec2(0, edge)}, 3);
                break;
            default:
                ps.set(new Vec2[]{new Vec2(0, 0), new Vec2(edge, edge), new Vec2(0, edge)}, 3);
        }

        FixtureDef fd = new FixtureDef();
        fd.shape = ps;
        fd.friction = 0.3f;
        fd.restitution = 0.8f;
        fd.density = 1;

        this.body = Manager.world.createBody(bd);
        this.body.createFixture(fd);
    }

    @Override
    public void drawMe(Graphics2D g) {
        g.setColor(this.color);
        int x = (int) this.body.getPosition().x;
        int y = (int) this.body.getPosition().y;
        int e = (int) this.edge;
        switch (angle) {
            case 0:
                g.fillPolygon(new int[]{x, x, x + e}, new int[]{y, y + e, y}, 3);
                break;
            case 1:
                g.fillPolygon(new int[]{x, x + e, x + e}, new int[]{y, y, y + e}, 3);
                break;
            case 2:
                g.fillPolygon(new int[]{x + e, x + e, x}, new int[]{y, y + e, y + e}, 3);
                break;
            default:
                g.fillPolygon(new int[]{x, x + e, x}, new int[]{y, y + e, y + e}, 3);
        }

    }
}
