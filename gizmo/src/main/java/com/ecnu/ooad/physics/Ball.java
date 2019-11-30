package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.utils.BodyUtil;
import org.jbox2d.dynamics.Body;
import org.json.JSONObject;

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

    /**
     * Get current x position.
     * @return Position x.
     */
    public float getBallX() {
        return bodies[0].getPosition().x;
    }

    /**
     * Get current y position.
     * @return Position y.
     */
    public float getBallY() {
        return bodies[0].getPosition().y;
    }

    /**
     * Get ball's detail to draw. Including xy position and delimiter.
     * @return jsonObject including all drawing details.
     */
    @Override
    public JSONObject getCurrentDetail() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("condition", 1);
        jsonObject.put("x", (int) (bodies[0].getPosition().x - radius));
        jsonObject.put("y", (int) (bodies[0].getPosition().y - radius));
        jsonObject.put("delimiter", (int) (radius * 2));
        return jsonObject;
    }
}
