package com.ecnu.ant;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class connector {
    private int numberAnt;
    private double stickLength;
    private double[] velocity;
    private double[] position;
    private boolean[] MinIsGoingRight;
    private boolean[] MaxIsGoingRight;
    private GameControl wholeGame;
    private PlayGame oneGame;

    @RequestMapping(value = "getGameStatus", method = RequestMethod.POST)
    public String getGameStatus(@RequestBody String response) {
        JSONObject jsonObject = new JSONObject(response);

        this.numberAnt = jsonObject.getInt("numberAnt");
        JSONArray velocityList = jsonObject.getJSONArray("antVelocity");
        JSONArray positionList = jsonObject.getJSONArray("position");
        this.stickLength = jsonObject.getDouble("stickLength");

        this.velocity = new double[velocityList.length()];
        this.position = new double[positionList.length()];
        for (int i = 0; i < velocityList.length(); i++) {
            this.velocity[i] = velocityList.getDouble(i);
            System.out.println("The " + i + " velocity = " + this.velocity[i]);
        }
        for (int i = 0; i < positionList.length(); i++) {
            this.position[i] = positionList.getDouble(i);
            System.out.println("The " + i + " position = " + this.position[i]);
        }

        System.out.println("Get stickLength: " + this.stickLength);
        System.out.println("Get number Ant: " + this.numberAnt);

        this.wholeGame = new GameControl(this.numberAnt, this.velocity, 0.01, this.position, this.stickLength);
        this.wholeGame.enumerateGame();
        return "hello ant";
    }

    @PostMapping(value = "getGameData")
    public String getGameData() {
        System.out.println("Sending game data");
        JSONObject jsonObject = new JSONObject();

        String minTime = String.format("%.2f", this.wholeGame.getMinTime());
        String maxTime = String.format("%.2f", this.wholeGame.getMaxTime());
        this.MinIsGoingRight = this.wholeGame.getMinTimeDirection();
        this.MaxIsGoingRight = this.wholeGame.getMaxTimeDirection();
        String minTimeDirection = convertDirectionToString(this.MinIsGoingRight);
        String maxTimeDirection = convertDirectionToString(this.MaxIsGoingRight);

        jsonObject.append("minTime", minTime);
        jsonObject.append("maxTime", maxTime);
        jsonObject.append("minTimeDirection", minTimeDirection);
        jsonObject.append("maxTimeDirection", maxTimeDirection);
        jsonObject.append("position", this.position.clone());
        jsonObject.append("stickLength", this.stickLength);
        return jsonObject.toString();
    }

    @PostMapping(value = "startGame")
    public String startGame(@RequestBody String response) {
        JSONObject jsonObject = new JSONObject(response);
        String direction = jsonObject.getString("direction");

        if (direction.equals("min"))
            this.oneGame = new PlayGame(this.numberAnt, this.velocity, this.MinIsGoingRight, this.position, this.stickLength, 0.01);
        else
            this.oneGame = new PlayGame(this.numberAnt, this.velocity, this.MaxIsGoingRight, this.position, this.stickLength, 0.01);

        return jsonObject.toString();
    }

    @PostMapping(value = "getPosition")
    public String getPosition() {
        JSONObject jsonObject = new JSONObject();
        double[] pos = oneGame.playAnimationGame();
        boolean gameOver = oneGame.getGameOver();
        if (gameOver) {
            jsonObject.append("gameOver", true);
            jsonObject.append("position", this.position);
        } else {
            jsonObject.append("gameOver", false);
            jsonObject.append("position", pos);
        }
        return jsonObject.toString();
    }

    private String convertDirectionToString(boolean[] isGoingRight) {
        String direction = "";
        for (int i = 0; i < this.numberAnt; i++) {
            if (i > 0)
                direction = direction + ", ";
            direction = direction + (isGoingRight[i] ? "Right" : "Left");
        }
        return direction;
    }
}
