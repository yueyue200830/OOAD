package com.ecnu.ooad.utils;

import com.ecnu.ooad.Controller;
import com.ecnu.ooad.physics.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yiqing Tao
 * @date 2019-11-19 20:10
 */
public class TransformUtil {
    private Controller controller;

    @Contract(pure = true)
    public TransformUtil(Controller controller) {
        this.controller = controller;
    }

    public String objectToJson(@NotNull List<Object> objectList) {
        JSONArray jsonArray = new JSONArray();
        for (Object o : objectList) {
            JSONObject jsonObject = new JSONObject();
            if (o instanceof Ball) {
                Ball ball = (Ball) o;
                jsonObject.put("type", "ball");
                jsonObject.put("subType", "ball");
                jsonObject.put("positionX", ball.getPositionX());
                jsonObject.put("positionY", ball.getPositionY());
                jsonObject.put("scaleRate", ball.getScaleRate());
            } else if (o instanceof Tool) {
                Tool tool = (Tool) o;
                jsonObject.put("scaleRate", tool.getScaleRate());
                jsonObject.put("type", "tool");
                jsonObject.put("positionX", tool.getPositionX());
                jsonObject.put("positionY",tool.getPositionY());
                jsonObject.put("direction", tool.getDirection());
                if (tool instanceof Diamond) {
                    jsonObject.put("subType", "diamond");
                } else if (tool instanceof Emerald) {
                    jsonObject.put("subType", "emerald");
                } else if (tool instanceof HinderLeft) {
                    jsonObject.put("subType", "hinderLeft");
                } else if (tool instanceof HinderRight) {
                    jsonObject.put("subType", "hinderRight");
                } else if (tool instanceof Slope) {
                    jsonObject.put("subType", "slope");
                } else if (tool instanceof StraightTrack) {
                    jsonObject.put("subType", "straightTrack");
                } else if(tool instanceof CurveTrack) {
                    jsonObject.put("subType", "curveTrack");
                } else if (tool instanceof Hole) {
                    jsonObject.put("subType", "absorber");
                }
            }
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    public void jsonToObject(String jsonString) {
        JSONArray jsonArray = new JSONArray(jsonString);
        List<Object> objectList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String type = jsonObject.getString("type");
            float positionX = jsonObject.getFloat("positionX");
            float positionY = jsonObject.getFloat("positionY");
            float scaleRate = jsonObject.getInt("scaleRate");
            int[] position = {(int) positionX, (int) positionY};
            if ("ball".equals(type)) {
                this.controller.addBall(position, scaleRate);
            } else if ("tool".equals(type)) {
                String subType = jsonObject.getString("subType");
                int direction = jsonObject.getInt("direction");
                if ("diamond".equals(subType)) {
                    this.controller.addTool(IngredientCondition.Diamond.getValue(), position, scaleRate);
                } else if ("emerald".equals(subType)) {
                    this.controller.addTool(IngredientCondition.Emerald.getValue(), position, scaleRate);
                } else if ("hinderLeft".equals(subType)) {
                    this.controller.addTool(IngredientCondition.HinderLeft.getValue(), position, scaleRate);
                } else if ("hinderRight".equals(subType)) {
                    this.controller.addTool(IngredientCondition.HinderRight.getValue(), position, scaleRate);
                } else if ("slope".equals(subType)) {
                    this.controller.addTool(IngredientCondition.Slope.getValue(), position, scaleRate, direction);
                } else if ("straightTrack".equals(subType)) {
                   this.controller.addTool(IngredientCondition.StraightTrack.getValue(), position, scaleRate, direction);
                } else if ("curveTrack".equals(subType)) {
                    this.controller.addTool(IngredientCondition.CurveTrack.getValue(), position, scaleRate, direction);
                } else if ("absorber".equals(subType)) {
                    this.controller.addTool(IngredientCondition.Absorber.getValue(), position, scaleRate, direction);
                }
            }
        }
    }
}
