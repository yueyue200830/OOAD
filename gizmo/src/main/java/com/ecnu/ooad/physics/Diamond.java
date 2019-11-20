package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.utils.BodyUtil;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-16 10:56
 */
public class Diamond extends Obstacle {

    private float radius;
    private Color color;

    public Diamond(float x, float y,float scaleRate) {
        super(x, y, scaleRate);
        this.radius = Constants.DIAMOND_RADIUS;
        this.color = Color.white;
        this.initDiamond(x, y);
    }

    private void initDiamond(float worldX, float worldY) {
      this.bodies[0] = BodyUtil.initCircle(worldX, worldY, this.radius, true);

    }

    public float getInnerX() {
        return bodies[0].getPosition().x - this.radius;
    }

    public float getInnerY() {
        return bodies[0].getPosition().y - this.radius;
    }

    public float getDelimiter() {
        return this.radius * 2;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public void drawMe(@NotNull Graphics2D g) {
        g.setColor(this.color);
        g.fillOval((int) this.getInnerX(), (int) this.getInnerY(), (int) this.getDelimiter(), (int) this.getDelimiter());
    }
}
