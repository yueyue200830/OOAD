package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.utils.BodyUtil;
import org.json.JSONObject;

/**
 * @author Yiqing Tao
 * @date 2019-11-16 10:56
 */
public class Diamond extends Obstacle {

    private float radius;

    /**
     * Diamond constructor
     * @param x the x axis position of diamond
     * @param y the y axis position of diamond
     * @param scaleRate scaling rate
     */
    public Diamond(float x, float y, float scaleRate) {
        super(x, y, scaleRate);
        this.radius = Constants.DIAMOND_RADIUS * scaleRate;
        this.type = 4;
        this.initDiamond();
    }

    /**
     * Init diamond body in jbox2d engine.
     */
    private void initDiamond() {
        float x = this.positionX + this.radius;
        float y = this.positionY + this.radius;

        this.bodies[0] = BodyUtil.initCircle(x, y, this.radius, true);
    }

    /**
     * Get diamond's detail to draw.
     * @return jsonObject including all drawing details.
     */
    @Override
    public JSONObject getCurrentDetail() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("condition", 4);
        jsonObject.put("x", (int) (bodies[0].getPosition().x - radius));
        jsonObject.put("y", (int) (bodies[0].getPosition().y - radius));
        jsonObject.put("delimiter", (int) (radius * 2));
        return jsonObject;
    }
}
