package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.utils.IngredientCondition;
import org.jbox2d.dynamics.Body;
import org.json.JSONObject;

/**
 * @author Yiqing Tao
 * @date 2019-11-14 16:51
 */
public class Hole extends Tool {

    private int length;
    private float centerX;
    private  float centerY;

    /**
     * Constructor of Hole.
     * @param x the x axis of left corner of the hole.
     * @param y the y axis of left corner of the hole.
     * @param scaleRate the scale rate of hole.
     */
    public Hole(float x, float y, float scaleRate) {
        super(x, y, 1);
        this.type = 2;
        this.length = Constants.GRID_LENGTH;
        this.centerX = this.positionX + this.length / 2;
        this.centerY = this.positionY + this.length / 2;
        this.bodies = new Body[0];
    }

    /**
     * Judge whether the ball is falling into the absorber.
     * @param ballX  the X axis center position of ball.
     * @param ballY  ths y axis center position of ball.
     * @return true if falling.
     */
    public boolean attach(float ballX, float ballY) {
        float distanceX = Math.abs(ballX - this.centerX);
        float distanceY = Math.abs(ballY - this.centerY);
        return distanceX <= 10 && distanceY <= 10;
    }

    /**
     * Get hole's detail to draw.
     * @return jsonObject including all drawing details.
     */
    @Override
    public JSONObject getCurrentDetail() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("condition", IngredientCondition.Absorber.getValue());
        jsonObject.put("x", (int) this.positionX);
        jsonObject.put("y", (int) this.positionY);
        jsonObject.put("width", this.length);
        jsonObject.put("height", this.length);
        return jsonObject;
    }
}
