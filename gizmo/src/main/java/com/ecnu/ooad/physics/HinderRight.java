package com.ecnu.ooad.physics;

import com.ecnu.ooad.utils.IngredientCondition;
import org.json.JSONObject;

/**
 * @author Yiqing Tao
 * @date 2019-11-14 16:51
 */
public class HinderRight extends Hinder {

    /**
     * The constructor of hinderRight.
     * @param x the x axis position of hinderRight.
     * @param y the y axis position of hinderRight.
     */
    public HinderRight(float x, float y) {
        super(x, y);
        this.type = 9;
    }

    /**
     * Get hinder's detail to draw. Put condition.
     * @return jsonObject including all drawing details.
     */
    @Override
    public JSONObject getCurrentDetail() {
        JSONObject jsonObject = super.getCurrentDetail();
        jsonObject.put("condition", IngredientCondition.HinderRight.getValue());
        return jsonObject;
    }
}
