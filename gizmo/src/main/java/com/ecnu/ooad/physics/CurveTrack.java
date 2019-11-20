package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.utils.BodyUtil;
import org.jbox2d.dynamics.Body;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Yiqing Tao, Jiayi Zhu
 * @date 2019-11-16 10:55
 */
public class CurveTrack extends Tool {

    private Color color = Color.yellow;
    private float wallWidth;
    private float length;

    public CurveTrack(float x, float y, int direction, float scaleRate) {
        super(x, y, scaleRate);
        this.direction = direction;
        this.bodies = new Body[3];
        this.wallWidth = Constants.TRACK_WIDTH / 2;
        this.length = Constants.GRID_LENGTH * this.scaleRate;
        this.initCurveTrack();
    }


    private void initCurveTrack() {
        float edge = (float) (10 - 4 * Math.sqrt(2) + 4f) * this.scaleRate;
        float x1, y1;
        float x2, y2;
        float x3, y3;

        switch (direction) {
            case 0:
                x1 = this.positionX + this.length - edge;
                y1 = this.positionY;
                x2 = this.positionX + this.length / 2;
                y2 = this.positionY + this.wallWidth / 2;
                x3 = this.positionX + this.length - this.wallWidth / 2;
                y3 = this.positionY + this.length / 2;
                break;
            case 1:
                x1 = this.positionX;
                y1 = this.positionY;
                x2 = this.positionX + this.length / 2;
                y2 = this.positionY + this.wallWidth / 2;
                x3 = this.positionX + this.wallWidth / 2;
                y3 = this.positionY + this.length / 2;
                break;
            case 2:
                x1 = this.positionX;
                y1 = this.positionY + this.length - edge;
                x2 = this.positionX + this.length / 2;
                y2 = this.positionY + this.length - this.wallWidth / 2;
                x3 = this.positionX + this.wallWidth / 2;
                y3 = this.positionY + this.length / 2;
                break;
            default:
                x1 = this.positionX + this.length - edge;
                y1 = this.positionY + this.length - edge;
                x2 = this.positionX + this.length / 2;
                y2 = this.positionY + this.length - this.wallWidth / 2;
                x3 = this.positionX + this.length - this.wallWidth / 2;
                y3 = this.positionY + this.length / 2;
        }

        this.bodies[0] = BodyUtil.initTriangle(x1, y1, edge, this.direction);
        this.bodies[1] = BodyUtil.initRectangle(x2, y2, this.length, this.wallWidth);
        this.bodies[2] = BodyUtil.initRectangle(x3, y3, this.wallWidth, this.length);
    }

    @Override
    public void drawMe(@NotNull Graphics2D g) {
        g.setColor(color);
        Stroke stroke = new BasicStroke(2);
        g.setStroke(stroke);

        int x, y, startAngle;
        int width = (int) this.length * 2 - 2;
        int height = (int) this.length * 2 - 2;
        int arcAngle = 90;
        int dotx, doty;

        if (direction == 0) {
            x = (int) this.positionX - (int) this.length + 1;
            y = (int) this.positionY + 1;
            startAngle = 0;
            dotx = (int) this.positionX;
            doty = (int) this.positionY + (int) this.length - 2;
        } else if (direction == 1) {
            x = (int) this.positionX + 1;
            y = (int) this.positionY + 1;
            startAngle = 90;
            dotx = (int) this.positionX + (int) this.length - 2;
            doty = (int) this.positionY + (int) this.length - 2;
        } else if (direction == 2) {
            x = (int) this.positionX + 1;
            y = (int) this.positionY - (int) this.length + 1;
            startAngle = 180;
            dotx = (int) this.positionX + (int) this.length - 2;
            doty = (int) this.positionY;
        } else {
            x = (int) this.positionX - (int) this.length + 1;
            y = (int) this.positionY - (int) this.length + 1;
            startAngle = 270;
            dotx = (int) this.positionX;
            doty = (int) this.positionY;
        }

        g.drawArc(x, y, width, height, startAngle, arcAngle);
        g.fillRect(dotx, doty, 2, 2);
    }
}
