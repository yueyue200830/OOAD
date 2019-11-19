package com.ecnu.ooad.Utils;

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

    public static String objectToJson(Object[] objectList) {
        JSONArray jsonArray = new JSONArray();
        for(Object o : objectList) {
            JSONObject jsonObject = new JSONObject();
            if(Ball.class.isInstance(o)) {
                Ball ball = (Ball) o;
                jsonObject.put("type", "ball");
                jsonObject.put("subType", "ball");
                jsonObject.put("positionX", ball.getPositionX());
                jsonObject.put("positionY",ball.getPositionX());
                jsonObject.put("radius",ball.getRadius());
            } else if(Tool.class.isInstance(o)) {
                Tool tool = (Tool) o;
                jsonObject.put("type","tool");
                jsonObject.put("positionX", tool.getPositionX());
                jsonObject.put("positionY",tool.getPositionX());
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
            if(type.equals("ball")) {
                Ball ball = new Ball(jsonObject.getFloat("positionX"), jsonObject.getFloat("positionY"));
                objectList.add(ball);
            } else if(type.equals("tool")) {
                String subType = jsonObject.getString("subType");
                if(subType.equals("diamond")) {
                    Diamond diamond = new Diamond(positionX, positionY);
                    objectList.add(diamond);
                } else if(subType.equals("emerald")) {
                    Emerald emerald = new Emerald(positionX,positionY);
                    objectList.add(emerald);
                } else if(subType.equals("hinderLeft")) {
                    HinderLeft hinderLeft = new HinderLeft(positionX, positionY);
                    objectList.add(hinderLeft);
                } else if(subType.equals("hinderRight")) {
                    HinderRight hinderRight = new HinderRight(positionX, positionY);
                    objectList.add(hinderRight);
                } else if(subType.equals("slope")) {
                    Slope slope = new Slope(positionX,positionY);
                    objectList.add(slope);
                } else if(subType.equals("straightTrack")) {
                    StraightTrack straightTrack = new StraightTrack(positionX,positionY,jsonObject.getInt("direction"));
                    objectList.add(straightTrack);
                } else if(subType.equals("curveTrack")) {
                    CurveTrack curveTrack = new CurveTrack(positionX, positionY, jsonObject.getInt("direction"));
                    objectList.add(curveTrack);
                }
            }
        }
        return objectList;
    }
}
