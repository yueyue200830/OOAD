package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.utils.BodyUtil;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-14 16:51
 */
public class Emerald extends Obstacle {

    private float height;
    private float width;
    private Color color;

    /**
     * The constructor of emerald
     * @param x the x axis position of emerald
     * @param y the y axis position of emerald
     * @param scaleRate the scaling rate
     */
    public Emerald(float x, float y, float scaleRate) {
        super(x, y, scaleRate);
        this.height = Constants.EMERALD_HEIGHT * scaleRate;
        this.width = Constants.EMERALD_WIDTH * scaleRate;
        this.color = Color.green;
        this.type = 5;
        this.initEmerald();
    }

    /**
     * Init the emerald body in jbox2d engine.
     */
    private void initEmerald() {
        float x = this.positionX + this.width / 2;
        float y = this.positionY + this.height / 2;
        this.bodies[0] = BodyUtil.initRectangle(x, y, this.width, this.height);
    }

    /**
     * Draw the emerald on the board.
     * @param g graphic g.
     */
    @Override
    public void drawMe(@NotNull Graphics2D g) {
        int x = (int) (this.bodies[0].getPosition().x - this.width / 2);
        int y = (int) (this.bodies[0].getPosition().y - this.width / 2);

        g.setColor(this.color);
        g.fillRect(x, y, (int) this.width, (int) this.height);
    }
}
