package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.Manager;
import com.ecnu.ooad.Utils.BodyUtil;
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
        super(x, y);
        this.direction = direction % 2;
        this.color = Color.yellow;
        float xLeft, yLeft, xRight, yRight;
        if (this.direction == 0) {
            xLeft = x - Constants.GRID_LENGTH / 2 + Constants.TRACK_WIDTH / 4;
            yLeft = y;
            xRight = x + Constants.GRID_LENGTH / 2 - Constants.TRACK_WIDTH / 4;
            yRight = y;
        } else {
            xLeft = x;
            yLeft = y - Constants.GRID_LENGTH / 2 + Constants.TRACK_WIDTH / 4;
            xRight = x;
            yRight = y + Constants.GRID_LENGTH / 2 - Constants.TRACK_WIDTH / 4;
        }
        this.initStraightTrack(xLeft, xRight, yLeft, yRight);
    }

    private void initStraightTrack(float xLeft, float xRight, float yLeft, float yRight) {
        float boxWidth;
        float boxHeight;
        if(this.direction == 0) {
            boxWidth = Constants.HINDER_WIDTH / 2;
            boxHeight = Constants.GRID_LENGTH;
        } else {
            boxWidth = Constants.GRID_LENGTH;
            boxHeight = Constants.TRACK_WIDTH / 2;
        }
        this.leftBody = BodyUtil.initRectangle(xLeft, yLeft, boxWidth, boxHeight);
        this.rightBody = BodyUtil.initRectangle(xRight, yRight, boxWidth, boxHeight);

    }

    @Override
    public void drawMe(Graphics2D g) {
        g.setColor(this.color);
        float leftX = this.leftBody.getPosition().x;
        float leftY = this.leftBody.getPosition().y;
        float rightX = this.rightBody.getPosition().x;
        float rightY = this.rightBody.getPosition().y;
        float width = Constants.TRACK_WIDTH;
        int length = Constants.GRID_LENGTH;

        if (this.direction == 0) {
            g.fillRect((int) (leftX - width / 4), (int) (leftY - length / 2), (int) width, length);
            g.fillRect((int) (rightX - width / 4 * 3), (int) (rightY - length / 2), (int) width, length);
        } else {
            g.fillRect((int) (leftX - length / 2), (int) (leftY - width / 4), length, (int) width);
            g.fillRect((int) (rightX - length / 2), (int) (rightY - width / 4 * 3), length, (int) width);
        }
    }
}
