package com.ecnu.ooad.Utils;

import com.ecnu.ooad.Manager;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

/**
 * @author Yiqing Tao
 * @date 2019-11-19 13:35
 */

public class BodyUtil {

    public static Body initRectangle(float x, float y, float boxWidth, float boxHeight){
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(x, y);
        bd.type = BodyType.STATIC;
        FixtureDef fd = new FixtureDef();
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(boxWidth / 2, boxHeight / 2);
        fd.shape = ps;

        Body currentBody = Manager.world.createBody(bd);
        currentBody.createFixture(fd);
        return currentBody;

    }

    public static Body initTriangle(float x, float y, float edge, int direction) {
        BodyDef bd = new BodyDef();
        bd.position.set(new Vec2(x - edge / 2, y - edge / 2));
        bd.type = BodyType.STATIC;

        PolygonShape ps = new PolygonShape();
        switch (direction) {
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

        Body currentBody = Manager.world.createBody(bd);
        currentBody.createFixture(fd);
        return currentBody;
    }

    public static Body initCircle(float x, float y, float radius, boolean isStatic) {
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(x, y);
        FixtureDef fd = new FixtureDef();
        CircleShape cs = new CircleShape();
        cs.m_radius = radius;
        fd.shape = cs;
        fd.density = 0.5f;
        fd.friction = 0.3f;
        fd.restitution = 0.6f;

        if(isStatic) {
            bd.type = BodyType.STATIC;
        } else {
            bd.type = BodyType.DYNAMIC;
        }

        Body currentBody = Manager.world.createBody(bd);
        currentBody.createFixture(fd);
        return currentBody;

    }
}
