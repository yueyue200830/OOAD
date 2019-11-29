package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.utils.BodyUtil;
import org.jbox2d.dynamics.Body;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jiayi Zhu, Yiqing Tao
 * @date 2019-11-16 20:34
 */
public class Ball extends Substance {

    private float radius;

    /**
     * This is the moving ball class.
     * @param x Position x.
     * @param y Position y.
     * @param scaleRate Scale rate.
     */
    public Ball(float x, float y, float scaleRate) {
        super(x, y, scaleRate);
        this.radius = Constants.GRID_LENGTH * scaleRate / 2 - Constants.CIRCLE_MARTIN;
        this.bodies = new Body[1];
        this.isStatic = true;
        this.initBall();
    }

    /**
     * Initialize ball's body in engine.
     */
    private void initBall() {
        float x = positionX + radius + Constants.CIRCLE_MARTIN;
        float y = positionY + radius + Constants.CIRCLE_MARTIN;
        this.bodies[0] = BodyUtil.initCircle(x, y, radius, false);
    }

    public float getBallX() {
        return bodies[0].getPosition().x;
    }

    public float getBallY() {
        return bodies[0].getPosition().y;
    }

    /**
     * Draw ball.
     * @param g Graphics tool.
     * @param panel Game panel.
     */
    @Override
    public void drawMe(@NotNull Graphics2D g, JPanel panel) {
        int x = (int) (bodies[0].getPosition().x - radius);
        int y = (int) (bodies[0].getPosition().y - radius);
        int delimiter = (int) (radius * 2);

        Image image = Toolkit.getDefaultToolkit().getImage("src/main/resources/ball.png");
        g.drawImage(image, x, y, delimiter, delimiter, panel);
    }
}
