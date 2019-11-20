package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.utils.BodyUtil;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Yiqing Tao, Jiayi Zhu
 * @date 2019-11-14 16:51
 */
public class Slope extends Obstacle {

    private float edge;
    private Color color;

    public Slope(float worldX, float worldY) {
        this(worldX, worldY, 1, 0);
    }

    public Slope(float worldX, float worldY, float scaleRate, int direction) {
        super(worldX, worldY, scaleRate);
        this.direction = direction;
        this.color = Color.WHITE;
        this.edge = Constants.EDGE;
        initSlope(worldX, worldY);
    }

    private void initSlope(float worldX, float worldY) {
        this.bodies[0] = BodyUtil.initTriangle(worldX, worldY, this.edge, this.direction);
    }

    public float getEdge() {
        return edge;
    }

    @Override
    public void drawMe(@NotNull Graphics2D g) {
        g.setColor(this.color);
        int x = (int) this.bodies[0].getPosition().x;
        int y = (int) this.bodies[0].getPosition().y;
        int e = (int) this.edge;
        switch (direction) {
            case 0:
                g.fillPolygon(new int[]{x, x, x + e}, new int[]{y, y + e, y}, 3);
                break;
            case 1:
                g.fillPolygon(new int[]{x, x + e, x + e}, new int[]{y, y, y + e}, 3);
                break;
            case 2:
                g.fillPolygon(new int[]{x + e, x + e, x}, new int[]{y, y + e, y + e}, 3);
                break;
            default:
                g.fillPolygon(new int[]{x, x + e, x}, new int[]{y, y + e, y + e}, 3);
        }
    }
}
