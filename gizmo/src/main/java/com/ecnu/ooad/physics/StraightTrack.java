package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.utils.BodyUtil;
import org.jbox2d.dynamics.Body;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yiqing Tao, Jiayi Zhu
 * @date 2019-11-14 16:51
 */
public class StraightTrack extends Tool {

    private Color color;
    private float boxWidth;
    private float boxHeight;
    private int drawWidth;
    private int drawHeight;

    /**
     * This is the straight track that will make the ball goes along the track.
     * @param x Position x.
     * @param y Position y.
     * @param direction Direction.
     * @param scaleRate Scale rate.
     */
    public StraightTrack(float x, float y, int direction, float scaleRate) {
        super(x, y, scaleRate);
        this.direction = direction % 2;
        this.color = new Color(178, 136, 80);
        this.bodies = new Body[2];
        this.type = 6;

        if (this.direction == 0) {
            this.boxWidth = Constants.TRACK_WIDTH / 2;
            this.boxHeight = Constants.GRID_LENGTH * scaleRate;
            this.drawWidth = (int) this.boxWidth * 2;
            this.drawHeight = (int) this.boxHeight;
        } else {
            this.boxWidth = Constants.GRID_LENGTH * scaleRate;
            this.boxHeight = Constants.TRACK_WIDTH / 2;
            this.drawWidth = (int) this.boxWidth;
            this.drawHeight = (int) this.boxHeight * 2;
        }

        this.initStraightTrack();
    }

    /**
     * Initialize track's body in the engine.
     */
    private void initStraightTrack() {
        float x1, y1, x2, y2;
        if (this.direction == 0) {
            x1 = positionX + this.boxWidth / 2;
            y1 = positionY + this.boxHeight / 2;
            x2 = positionX + this.boxHeight - this.boxWidth / 2;
            y2 = positionY + this.boxHeight / 2;
        } else {
            x1 = positionX + this.boxWidth / 2;
            y1 = positionY + this.boxHeight / 2;
            x2 = positionX + this.boxWidth / 2;
            y2 = positionY + this.boxWidth - this.boxHeight / 2;
        }

        this.bodies[0] = BodyUtil.initRectangle(x1, y1, boxWidth, boxHeight);
        this.bodies[1] = BodyUtil.initRectangle(x2, y2, boxWidth, boxHeight);
    }

    /**
     * Draw track.
     * @param g Graphics tool.
     * @param panel Game panel.
     */
    @Override
    public void drawMe(@NotNull Graphics2D g, JPanel panel) {
        g.setColor(this.color);
        float x1 = this.bodies[0].getPosition().x;
        float y1 = this.bodies[0].getPosition().y;
        float x2 = this.bodies[1].getPosition().x;
        float y2 = this.bodies[1].getPosition().y;

        if (this.direction == 0) {
            g.fillRect((int) (x1 - boxWidth / 4), (int) (y1 - boxHeight / 2), drawWidth, drawHeight);
            g.fillRect((int) (x2 - boxWidth / 4 * 3), (int) (y2 - boxHeight / 2), drawWidth, drawHeight);
        } else {
            g.fillRect((int) (x1 - boxWidth / 2), (int) (y1 - boxHeight / 4), drawWidth, drawHeight);
            g.fillRect((int) (x2 - boxWidth / 2), (int) (y2 - boxHeight / 4 * 3), drawWidth, drawHeight);
        }
    }
}
