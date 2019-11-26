package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.utils.BodyUtil;
import org.jbox2d.dynamics.Body;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-14 16:51
 */
public class Hinder extends Tool {
    private Color color;
    private float width;
    private float height;

    /**
     * The constructor of Hinder
     * @param x The x axis of position of Hinder
     * @param y The y axis of position of Hinder
     */
    public Hinder(float x, float y) {
        super(x, y, 1);
        this.color = Color.LIGHT_GRAY;
        this.width = Constants.HINDER_WIDTH;
        this.height = Constants.HINDER_HEIGHT;
        this.bodies = new Body[1];
        this.initHinder();
    }

    /**
     * Init the hinder in jbox2d engine.
     */
    private void initHinder() {
        float leftCornerX = this.positionX + this.width / 2;
        float leftCornerY = this.positionY + Constants.GRID_LENGTH - this.height / 2;
        this.bodies[0] = BodyUtil.initRectangle(leftCornerX, leftCornerY, this.width, this.height);
    }

    /**
     * Draw hinder on board.
     * @param g Graphics tool.
     */
    @Override
    public void drawMe(@NotNull Graphics2D g) {
        int x = (int) (this.bodies[0].getPosition().x - this.width / 2);
        int y = (int) (this.bodies[0].getPosition().y - this.height / 2);

        g.setColor(this.color);
        g.fillRect(x, y, (int) this.width, (int) this.height);
    }
}
