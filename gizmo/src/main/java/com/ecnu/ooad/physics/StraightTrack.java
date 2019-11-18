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
public class StraightTrack extends Track {
    private Body leftBody;
    private Body rightBody;
    private Color color;

    public StraightTrack(float x, float y) {
        float xLeft = x - Constants.GRID_LENGTH / 2;
        float yLeft = y;
        float xRight = x + Constants.GRID_LENGTH / 2;
        float yRight = y;
        this.initStraightTrack(xLeft, xRight, yLeft ,yRight);

    }
    public void initStraightTrack(float xLeft, float xRight, float yLeft, float yRight) {
        this.color = Color.yellow;
        BodyDef bdLeft = new BodyDef();
        BodyDef bdRight = new BodyDef();
        bdLeft.position = new Vec2(xLeft,yLeft);
        bdRight.position = new Vec2(xRight,yRight);
        bdLeft.type = BodyType.STATIC;
        bdRight.type = BodyType.STATIC;
        FixtureDef fdLeft = new FixtureDef();
        FixtureDef fdRight = new FixtureDef();
        PolygonShape psLeft = new PolygonShape();
        PolygonShape psRight = new PolygonShape();
        psLeft.setAsBox(Constants.TRACK_WIDTH, Constants.GRID_LENGTH / 2);
        psRight.setAsBox(Constants.TRACK_WIDTH, Constants.GRID_LENGTH / 2);
        fdLeft.shape = psLeft;
        fdRight.shape = psRight;

        this.leftBody = Manager.world.createBody(bdLeft);
        this.rightBody = Manager.world.createBody(bdRight);
        this.leftBody.createFixture(fdLeft);
        this.rightBody.createFixture(fdRight);

    }
    @Override
    public void drawMe(Graphics2D g) {
        g.setColor(this.color);
        g.fillRect((int) this.leftBody.getPosition().x - (int) Constants.TRACK_WIDTH / 2, (int) this.leftBody.getPosition().y - (int) Constants.TRACK_WIDTH / 2, (int) Constants.TRACK_WIDTH, (int) Constants.GRID_LENGTH);
        g.fillRect((int) this.rightBody.getPosition().x - (int) Constants.TRACK_WIDTH / 2, (int) this.rightBody.getPosition().y - (int) Constants.TRACK_WIDTH / 2, (int) Constants.TRACK_WIDTH, (int) Constants.GRID_LENGTH);

    }
}
