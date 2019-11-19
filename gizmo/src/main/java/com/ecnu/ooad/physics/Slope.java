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
public class Slope extends Obstacle {

    private float edge;
    private Color color;

    public Slope(float worldX, float worldY) {
        this(worldX, worldY, 1, 0);
    }

    public Slope(float worldX, float worldY, float scaleRate, int direction) {
        super(worldX, worldY);
        this.direction = direction;
        this.color = Color.WHITE;
        this.edge = Constants.EDGE;
        initSlope(worldX, worldY);
    }

    private void initSlope(float worldX, float worldY) {
        this.body = BodyUtil.initTriangle(worldX, worldY, this.edge, this.direction);
    }

    @Override
    public void drawMe(Graphics2D g) {
        g.setColor(this.color);
        int x = (int) this.body.getPosition().x;
        int y = (int) this.body.getPosition().y;
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
