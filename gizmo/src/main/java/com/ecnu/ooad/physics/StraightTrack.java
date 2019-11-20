package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.utils.BodyUtil;
import org.jbox2d.dynamics.Body;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Yiqing Tao, Jiayi Zhu
 * @date 2019-11-14 16:51
 */
public class StraightTrack extends Tool {
    private Color color;
    private float boxWidth;
    private float boxHeight;

    public StraightTrack(float x, float y, int direction) {
        super(x, y);
        this.direction = direction % 2;
        this.color = Color.yellow;
        this.bodies = new Body[2];

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
        if(this.direction == 0) {
            boxWidth = Constants.TRACK_WIDTH / 2;
            boxHeight = Constants.GRID_LENGTH;
        } else {
            boxWidth = Constants.GRID_LENGTH;
            boxHeight = Constants.TRACK_WIDTH / 2;
        }
        this.bodies[0] = BodyUtil.initRectangle(xLeft, yLeft, boxWidth, boxHeight);
        this.bodies[1] = BodyUtil.initRectangle(xRight, yRight, boxWidth, boxHeight);

    }

    public float getBoxWidth() {
        return boxWidth;
    }

    public float getBoxHeight() {
        return boxHeight;
    }

    @Override
    public void drawMe(@NotNull Graphics2D g) {
        g.setColor(this.color);
        float leftX = this.bodies[0].getPosition().x;
        float leftY = this.bodies[0].getPosition().y;
        float rightX = this.bodies[1].getPosition().x;
        float rightY = this.bodies[1].getPosition().y;
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
