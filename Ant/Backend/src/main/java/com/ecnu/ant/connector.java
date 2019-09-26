package com.ecnu.ant;


import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;



import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class connector {

    @RequestMapping(value = "test", method = RequestMethod.POST)
    public String getTest(@RequestBody String response) {
        System.out.println("enter "  + response);
        JSONObject jsonObject = JSONObject.parseObject(response);

        int numberAnt = jsonObject.getInteger("numberAnt");
        JSONArray velocity = (JSONArray) jsonObject.get("antVelocity");
        JSONArray position = (JSONArray) jsonObject.get("position");
        double stickLength = (double) jsonObject.getInteger("stickLength");

        List<Integer> velocityList = JSONObject.parseArray(velocity.toJSONString(),Integer.class);
        List<Integer> positionList = JSONObject.parseArray(position.toJSONString(),Integer.class);

        double[] vList = new double[velocityList.size()];
        double[] pList = new double[positionList.size()];
        for(int i = 0; i<velocityList.size();i++){
            vList[i] = (double) velocityList.get(i);
            System.out.println("the " + i + "velocity = " + vList[i]);
        }
        for(int i = 0;i<positionList.size();i++){
            pList[i] =  (double) positionList.get(i);
            System.out.println("the " + i + "position = " + pList[i]);
        }
        System.out.println("get stickLength: " + stickLength);
        System.out.println("get number Ant: " + numberAnt);


        GameControl wholeGame = new GameControl(numberAnt,vList,0.01,pList,stickLength);
        wholeGame.enumerateGame();
        return "hello test";
    }
}
