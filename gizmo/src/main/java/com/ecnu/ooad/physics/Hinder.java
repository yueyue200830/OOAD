package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
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
public class Hinder implements Tool {
    private Body body;
    private Color color;

    public Hinder(float x, float y) {
        this.color = Color.LIGHT_GRAY;
        this.initHinder(x, y);
    }

    private void initHinder(float x, float y) {
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(x, y + Constants.GRID_LENGTH / 2 - Constants.HINDER_HEIGHT / 2);
        bd.type = BodyType.STATIC;
        FixtureDef fd = new FixtureDef();
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(Constants.HINDER_WIDTH / 2, Constants.HINDER_HEIGHT / 2);
        fd.shape = ps;

        this.body = Manager.world.createBody(bd);
        this.body.createFixture(fd);


    }
    @Override
    public void drawMe(Graphics2D g) {
        g.setColor(this.color);
        int x = (int) this.body.getPosition().x;
        int y = (int) this.body.getPosition().y;
        g.fillRect(x - Constants.HINDER_WIDTH / 2, y - Constants.HINDER_HEIGHT / 2, Constants.HINDER_WIDTH, Constants.HINDER_HEIGHT);
    }
}
