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
 * @author Yiqing Tao, Jiayi Zhu
 * @date 2019-11-14 16:51
 */
public class StraightTrack extends Track {
    private Body leftBody;
    private Body rightBody;
    private Color color;
    private int direction;

    public StraightTrack(float x, float y, int direction) {
        this.direction = direction % 2;
        float xLeft, yLeft, xRight, yRight;
        if (this.direction == 0) {
            xLeft = x - Constants.GRID_LENGTH / 2 + Constants.TRACK_WIDTH / 2;
            yLeft = y;
            xRight = x + Constants.GRID_LENGTH / 2 - Constants.TRACK_WIDTH / 2;
            yRight = y;
        } else {
            xLeft = x;
            yLeft = y - Constants.GRID_LENGTH / 2 + Constants.TRACK_WIDTH / 2;
            xRight = x;
            yRight = y + Constants.GRID_LENGTH / 2 - Constants.TRACK_WIDTH / 2;
        }
        this.initStraightTrack(xLeft, xRight, yLeft, yRight);
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
        if (this.direction == 0) {
            psLeft.setAsBox(Constants.TRACK_WIDTH / 2, Constants.GRID_LENGTH / 2);
            psRight.setAsBox(Constants.TRACK_WIDTH / 2, Constants.GRID_LENGTH / 2);
        } else {
            psLeft.setAsBox(Constants.GRID_LENGTH / 2, Constants.TRACK_WIDTH / 2);
            psRight.setAsBox(Constants.GRID_LENGTH / 2, Constants.TRACK_WIDTH / 2);
        }
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
        int leftX = (int) this.leftBody.getPosition().x;
        int leftY = (int) this.leftBody.getPosition().y;
        int rightX = (int) this.rightBody.getPosition().x;
        int rightY = (int) this.rightBody.getPosition().y;
        int width = (int) Constants.TRACK_WIDTH;
        int length = Constants.GRID_LENGTH;

        if (this.direction == 0) {
            g.fillRect(leftX - width / 2, leftY - length / 2, width, length);
            g.fillRect(rightX - width / 2, rightY - length / 2, width, length);
        } else {
            g.fillRect(leftX - length / 2, leftY - width / 2, length, width);
            g.fillRect(rightX - length / 2, rightY - width / 2, length, width);
        }
    }
}
