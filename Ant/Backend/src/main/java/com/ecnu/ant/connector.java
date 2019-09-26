package com.ecnu.ant;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class connector {
    GameControl wholeGame;

    @RequestMapping(value = "test", method = RequestMethod.POST)
    public String getTest(@RequestBody String response) {
        System.out.println("enter "  + response);
        JSONObject jsonObject = new JSONObject(response);

        int numberAnt = jsonObject.getInt("numberAnt");
        JSONArray velocityList = jsonObject.getJSONArray("antVelocity");
        JSONArray positionList = jsonObject.getJSONArray("position");
        double stickLength = jsonObject.getDouble("stickLength");

        double[] vList = new double[velocityList.length()];
        double[] pList = new double[positionList.length()];
        for (int i = 0; i < velocityList.length(); i++) {
            vList[i] = velocityList.getDouble(i);
            System.out.println("the " + i + "velocity = " + vList[i]);
        }
        for (int i = 0; i < positionList.length(); i++) {
            pList[i] = positionList.getDouble(i);
            System.out.println("the " + i + "position = " + pList[i]);
        }

        System.out.println("get stickLength: " + stickLength);
        System.out.println("get number Ant: " + numberAnt);

        wholeGame = new GameControl(numberAnt, vList, 0.01, pList, stickLength);
        wholeGame.enumerateGame();
        return "hello test";
    }

    @PostMapping(value = "getGameData")
    public String getGameData() {
        System.out.println("sending data");
        return "hello test";
    }

    @PostMapping(value = "getPosition")
    public String getPosition() {
        JSONObject jsonObject = new JSONObject();
        System.out.println("sending position");
        return "hello position";
    }
}
