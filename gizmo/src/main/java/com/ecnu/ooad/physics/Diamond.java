package com.ecnu.ooad.physics;

import com.ecnu.ooad.Manager;
import com.ecnu.ooad.Utils.BodyUtil;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.awt.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-16 10:56
 */
public class Diamond extends Obstacle {

    private float radius;
    private Color color;

    public Diamond(float x, float y) {
        super(x, y);
        this.radius = 10f;
        this.color = Color.white;
        this.initDiamond(x, y);
    }

    private void initDiamond(float worldX, float worldY) {
      this.body = BodyUtil.initCircle(worldX, worldY, this.radius, true);

    }

    public float getInnerX() {
        return body.getPosition().x - this.radius;
    }

    public float getInnerY() {
        return body.getPosition().y - this.radius;
    }

    public float getDelimiter() {
        return this.radius * 2;
    }

    @Override
    public void drawMe(Graphics2D g) {
        g.setColor(this.color);
        g.fillOval((int) this.getInnerX(), (int) this.getInnerY(), (int) this.getDelimiter(), (int) this.getDelimiter());
    }
}
