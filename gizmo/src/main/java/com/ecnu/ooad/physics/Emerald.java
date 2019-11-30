package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.utils.BodyUtil;
import org.json.JSONObject;

/**
 * @author Yiqing Tao
 * @date 2019-11-14 16:51
 */
public class Emerald extends Obstacle {

    private float height;
    private float width;

    /**
     * The constructor of emerald
     * @param x the x axis position of emerald
     * @param y the y axis position of emerald
     * @param scaleRate the scaling rate
     */
    public Emerald(float x, float y, float scaleRate) {
        super(x, y, scaleRate);
        this.height = Constants.EMERALD_HEIGHT * scaleRate;
        this.width = Constants.EMERALD_WIDTH * scaleRate;
        this.type = 5;
        this.initEmerald();
    }

    /**
     * Init the emerald body in jbox2d engine.
     */
    private void initEmerald() {
        float x = this.positionX + this.width / 2;
        float y = this.positionY + this.height / 2;
        this.bodies[0] = BodyUtil.initRectangle(x, y, this.width, this.height);
    }

    /**
     * Get emerald's detail to draw.
     * @return jsonObject including all drawing details.
     */
    @Override
    public JSONObject getCurrentDetail() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("condition", 5);
        jsonObject.put("x", positionX);
        jsonObject.put("y", positionY);
        jsonObject.put("width", (int) this.width);
        jsonObject.put("height", (int) this.height);
        return jsonObject;
    }
}
