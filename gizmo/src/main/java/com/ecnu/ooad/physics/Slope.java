package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.utils.BodyUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * @author Yiqing Tao, Jiayi Zhu
 * @date 2019-11-14 16:51
 */
public class Slope extends Obstacle {

    private float edge;
    private Color color;

    /**
     * The constructor of slope.
     * @param worldX The x axis position of slope.
     * @param worldY The y axis position of slope.
     * @param scaleRate The scaling rate.
     * @param direction The direction of slope.
     */
    public Slope(float worldX, float worldY, float scaleRate, int direction) {
        super(worldX, worldY, scaleRate);
        this.direction = direction;
        this.color = new Color(252, 149, 12);
        this.edge = Constants.EDGE * scaleRate;
        this.type = 3;
        initSlope();
    }

    /**
     * Init slope in jbox2d engine.
     */
    private void initSlope() {
        this.bodies[0] = BodyUtil.initTriangle(positionX, positionY, this.edge, this.direction);
    }

    /**
     * Draw the slope on board.
     * @param g Graphics tool.
     * @param panel Game panel.
     */
    @Override
    public void drawMe(@NotNull Graphics2D g, JPanel panel) {
        g.setColor(this.color);
        int x = (int) this.bodies[0].getPosition().x;
        int y = (int) this.bodies[0].getPosition().y;
        int e = (int) this.edge;
        switch (direction) {
            case 0:
                g.fillPolygon(new int[]{x, x + e, x + e}, new int[]{y, y, y + e}, 3);
                break;
            case 1:
                g.fillPolygon(new int[]{x, x, x + e}, new int[]{y, y + e, y}, 3);
                break;
            case 2:
                g.fillPolygon(new int[]{x, x + e, x}, new int[]{y, y + e, y + e}, 3);
                break;
            default:
                g.fillPolygon(new int[]{x + e, x + e, x}, new int[]{y, y + e, y + e}, 3);
        }
    }
}
