package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.utils.BodyUtil;
import org.jbox2d.dynamics.Body;
import org.json.JSONObject;

/**
 * @author Yiqing Tao
 * @date 2019-11-14 16:51
 */
public class Hinder extends Tool {
    private float width;
    private float height;

    /**
     * The constructor of Hinder
     * @param x The x axis of position of Hinder
     * @param y The y axis of position of Hinder
     */
    public Hinder(float x, float y) {
        super(x, y, 1);
        this.width = Constants.HINDER_WIDTH;
        this.height = Constants.HINDER_HEIGHT;
        this.bodies = new Body[1];
        this.initHinder();
    }

    /**
     * Init the hinder in jbox2d engine.
     */
    private void initHinder() {
        float leftCornerX = this.positionX + this.width / 2;
        float leftCornerY = this.positionY + Constants.GRID_LENGTH - this.height / 2;
        this.bodies[0] = BodyUtil.initRectangle(leftCornerX, leftCornerY, this.width, this.height);
    }

    /**
     * Get hinder's detail to draw. It doesn't include condition, so should be override.
     * @return jsonObject including all drawing details.
     */
    @Override
    public JSONObject getCurrentDetail() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x", (int) (this.bodies[0].getPosition().x - this.width / 2));
        jsonObject.put("y", (int) (this.bodies[0].getPosition().y - this.height / 2));
        jsonObject.put("width", (int) this.width);
        jsonObject.put("height", (int) this.height);
        return jsonObject;
    }
}
