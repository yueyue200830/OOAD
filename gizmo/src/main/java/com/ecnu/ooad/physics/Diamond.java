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

    public Diamond(float x, float y, float scaleRate) {
        super(x, y, scaleRate);
        this.radius = Constants.DIAMOND_RADIUS * scaleRate;
        this.color = Color.white;
        this.type = 4;
        this.initDiamond();
    }

    private void initDiamond() {
        float x = this.positionX + this.radius;
        float y = this.positionY + this.radius;

        this.bodies[0] = BodyUtil.initCircle(x, y, this.radius, true);
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public void drawMe(@NotNull Graphics2D g) {
        int x = (int) (bodies[0].getPosition().x - radius);
        int y = (int) (bodies[0].getPosition().y - radius);
        int delimiter = (int) (radius * 2);

        g.setColor(this.color);
        g.fillOval(x, y, delimiter, delimiter);
    }
}
