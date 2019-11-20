package com.ecnu.ooad.utils;

import com.ecnu.ooad.physics.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yiqing Tao
 * @date 2019-11-19 20:10
 */
public class TransformUtil {

    public static String objectToJson(List<Object> objectList) {
        JSONArray jsonArray = new JSONArray();
        for(Object o : objectList) {
            JSONObject jsonObject = new JSONObject();
            if(Ball.class.isInstance(o)) {
                Ball ball = (Ball) o;
                jsonObject.put("type", "ball");
                jsonObject.put("subType", "ball");
                jsonObject.put("positionX", ball.getPositionX());
                jsonObject.put("positionY", ball.getPositionY());
                jsonObject.put("scaleRate", ball.getScaleRate());
                jsonObject.put("radius", ball.getRadius());
            } else if(Tool.class.isInstance(o)) {
                Tool tool = (Tool) o;
                jsonObject.put("scaleRate", tool.getScaleRate());
                jsonObject.put("type", "tool");
                jsonObject.put("positionX", tool.getPositionX());
                jsonObject.put("positionY",tool.getPositionY());
                jsonObject.put("direction", tool.getDirection());
                if(Diamond.class.isInstance(tool)) {
                    Diamond diamond = (Diamond) tool;
                    jsonObject.put("subType", "diamond");
                    jsonObject.put("radius", diamond.getRadius());
                } else if(Emerald.class.isInstance(tool)) {
                    Emerald emerald = (Emerald) tool;
                    jsonObject.put("subType", "emerald");
                    jsonObject.put("width", emerald.getWidth());
                    jsonObject.put("height", emerald.getHeight());
                } else if(HinderLeft.class.isInstance(tool)) {
                    HinderLeft hinderLeft =(HinderLeft) tool;
                    jsonObject.put("subType", "hinderLeft");
                    jsonObject.put("width", hinderLeft.getWidth());
                    jsonObject.put("height", hinderLeft.getHeight());
                } else if(HinderRight.class.isInstance(tool)) {
                    HinderRight hinderRight = (HinderRight) tool;
                    jsonObject.put("subType", "hinderRight");
                    jsonObject.put("width", hinderRight.getWidth());
                    jsonObject.put("height", hinderRight.getHeight());
                } else if(Slope.class.isInstance(tool)) {
                    Slope slope = (Slope) tool;
                    jsonObject.put("subType", "slope");
                    jsonObject.put("edge", slope.getEdge());
                } else if(StraightTrack.class.isInstance(tool)) {
                    StraightTrack straightTrack = (StraightTrack) tool;
                    jsonObject.put("subType", "straightTrack");
                    jsonObject.put("width", straightTrack.getBoxWidth());
                    jsonObject.put("height", straightTrack.getBoxHeight());
                } else if(CurveTrack.class.isInstance(tool)) {
                    CurveTrack curveTrack = (CurveTrack) tool;
                    jsonObject.put("subType", "curveTrack");
                    jsonObject.put("width", curveTrack.getPositionX());
                    jsonObject.put("height", curveTrack.getPositionY());
                }
            }
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }

    public static List<Object> jsonToObject(String jsonString) {
        JSONArray jsonArray = new JSONArray(jsonString);
        List<Object> objectList = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String type = jsonObject.getString("type");
            float positionX = jsonObject.getFloat("positionX");
            float positionY = jsonObject.getFloat("positionY");
            float scaleRate = jsonObject.getFloat("scaleRate");
            if("ball".equals(type)) {
                Ball ball = new Ball(jsonObject.getFloat("positionX"), jsonObject.getFloat("positionY"), scaleRate);
                objectList.add(ball);
            } else if("tool".equals(type)) {
                String subType = jsonObject.getString("subType");
                if("diamond".equals(subType)) {
                    Diamond diamond = new Diamond(positionX, positionY, scaleRate);
                    objectList.add(diamond);
                } else if("emerald".equals(subType)) {
                    Emerald emerald = new Emerald(positionX,positionY, scaleRate);
                    objectList.add(emerald);
                } else if("hinderLeft".equals(subType)) {
                    HinderLeft hinderLeft = new HinderLeft(positionX, positionY, scaleRate);
                    objectList.add(hinderLeft);
                } else if("hinderRight".equals(subType)) {
                    HinderRight hinderRight = new HinderRight(positionX, positionY, scaleRate);
                    objectList.add(hinderRight);
                } else if("slope".equals(subType)) {
                    Slope slope = new Slope(positionX,positionY);
                    objectList.add(slope);
                } else if("straightTrack".equals(subType)) {
                    StraightTrack straightTrack = new StraightTrack(positionX,positionY,jsonObject.getInt("direction"), scaleRate);
                    objectList.add(straightTrack);
                } else if("curveTrack".equals(subType)) {
                    CurveTrack curveTrack = new CurveTrack(positionX, positionY, jsonObject.getInt("direction"));
                    objectList.add(curveTrack);
                }
            }
        }
        return objectList;
    }
}
