package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.utils.BodyUtil;
import com.ecnu.ooad.utils.IngredientCondition;
import org.json.JSONObject;

/**
 * @author Yiqing Tao, Jiayi Zhu
 * @date 2019-11-14 16:51
 */
public class Slope extends Obstacle {

    private float edge;

    /**
     * The constructor of slope.
     * @param worldX The x axis position of slope.
     * @param worldY The y axis position of slope.
     * @param scaleRate The scaling rate.
     * @param direction The direction of slope.
     */
    public Slope(float worldX, float worldY, float scaleRate, int direction) {
        super(worldX, worldY, scaleRate);
        this.direction = direction;
        this.edge = Constants.EDGE * scaleRate;
        this.type = 3;
        initSlope();
    }

    /**
     * Init slope in jbox2d engine.
     */
    private void initSlope() {
        this.bodies[0] = BodyUtil.initTriangle(positionX, positionY, this.edge, this.direction);
    }

    /**
     * Get slope's detail to draw.
     * @return jsonObject including all drawing details.
     */
    @Override
    public JSONObject getCurrentDetail() {

        int x = (int) this.bodies[0].getPosition().x;
        int y = (int) this.bodies[0].getPosition().y;
        int e = (int) this.edge;
        int[] px;
        int[] py;

        switch (direction) {
            case 0:
                px = new int[]{x, x + e, x + e};
                py = new int[]{y, y, y + e};
                break;
            case 1:
                px = new int[]{x, x, x + e};
                py = new int[]{y, y + e, y};
                break;
            case 2:
                px = new int[]{x, x + e, x};
                py = new int[]{y, y + e, y + e};
                break;
            default:
                px = new int[]{x + e, x + e, x};
                py = new int[]{y, y + e, y + e};
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("condition", IngredientCondition.Slope.getValue());
        jsonObject.put("x", px);
        jsonObject.put("y", py);
        return jsonObject;
    }
}
