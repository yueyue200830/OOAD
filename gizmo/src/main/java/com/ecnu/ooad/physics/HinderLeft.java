package com.ecnu.ooad.physics;

import com.ecnu.ooad.utils.IngredientCondition;
import org.json.JSONObject;

/**
 * @author Yiqing Tao
 * @date 2019-11-14 16:51
 */
public class HinderLeft extends Hinder {

    /**
     * The constructor of hinderLeft.
     * @param x the x axis position of hinderLeft.
     * @param y the y axis position of hinderLeft.
     */
    public HinderLeft(float x, float y) {
        super(x, y);
        this.type = 8;
    }

    /**
     * Get hinder's detail to draw. Put condition.
     * @return jsonObject including all drawing details.
     */
    @Override
    public JSONObject getCurrentDetail() {
        JSONObject jsonObject = super.getCurrentDetail();
        jsonObject.put("condition", IngredientCondition.HinderLeft.getValue());
        return jsonObject;
    }
}
