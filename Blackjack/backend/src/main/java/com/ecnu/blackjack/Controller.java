package com.ecnu.blackjack;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jiayi Zhu, Yiqing Tao
 * @date 2019-10-27 17:25
 */
@RestController
@CrossOrigin(origins = "*")
public class Controller {
    private Deck deck;

    @PostMapping(value = "startGame")
    @ResponseBody
    public String startGame(@RequestBody String response) {

        JSONObject jsonObject = new JSONObject(response);
        int playerNumber = jsonObject.getInt("playerNumber");
        JSONArray playerHandNumber = jsonObject.getJSONArray("playerHandNumber");
        JSONArray betList = jsonObject.getJSONArray("betList");

        int[] handNumber = new int[playerNumber];
        for (int i = 0; i < playerNumber; i++) {
            handNumber[i] = playerHandNumber.getInt(i);
        }

        deck = new Deck(playerNumber, handNumber);

        int k = 0;
        for (int i = 0; i < playerNumber; i++) {
            int[] bet = new int[handNumber[i]];
            for (int j = 0; j < handNumber[i]; j++) {
                bet[j] = betList.getJSONObject(k++).getInt("bet");
            }
            deck.setBet(bet, i);
        }

        deck.initialDraw();

        List<List<List<Integer>>> info = deck.getRoundInfo(false);
        JSONObject jsonInfo = new JSONObject();
        jsonInfo.append("info", info);
        System.out.println(jsonInfo.toString());
        return jsonInfo.toString();
    }

    @RequestMapping(value = "/drawCard", method = RequestMethod.GET)
    @ResponseBody
    public String getOneCard(@RequestParam(name="playerNo") int playerNo, @RequestParam(name = "handNo") int handNo){
        System.out.println("receive " + playerNo + " " + handNo);
        int num = deck.basicDraw(playerNo, handNo, false);
        boolean largerThan21 = deck.getPlayerLose(playerNo, handNo);

        JSONObject jsonObject = new JSONObject();
        jsonObject.append("newCard", num);
        jsonObject.append("lose", largerThan21);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/doubleBet", method = RequestMethod.GET)
    @ResponseBody
    public String doubleBet(@RequestParam(name = "playerNo") int playerNo, @RequestParam(name = "handNo") int handNo){
        System.out.println("receive " + playerNo + " " + handNo);
        deck.doubleBet(playerNo,handNo);
        return "Success";
    }

    @RequestMapping(value = "/dealerTurn", method = RequestMethod.GET)
    @ResponseBody
    public String dealerTurn() {
        System.out.println("dealer turn");
        JSONObject jsonObject = new JSONObject();
        deck.askDealerToDraw();
        List<List<List<Integer>>> finalInfo = deck.getRoundInfo(true);
        jsonObject.append("finalInfo", finalInfo);
        boolean dealerLose = deck.getDealerLose();
        jsonObject.append("dealerLose", dealerLose);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/getWinner", method = RequestMethod.GET)
    @ResponseBody
    public String getWinner() {
        System.out.println("now get Winner");
        JSONObject jsonObject = new JSONObject();
        List<List<Integer>> winnerList = deck.judgeWin();
        jsonObject.append("winnerList", winnerList);
        System.out.println(winnerList);
        return jsonObject.toString();
    }
}
