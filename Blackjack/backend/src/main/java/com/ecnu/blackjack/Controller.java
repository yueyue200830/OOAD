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
    public void startGame(@RequestBody String response) {

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
    }

    @RequestMapping(value = "/drawCard", method = RequestMethod.GET)
    @ResponseBody
    public int getOneCard(@RequestParam(name="playerNo") int playerNo, @RequestParam(name = "handNo") int handNo){
        System.out.println("receive " + playerNo + " " + handNo);
        return deck.basicDraw(playerNo,handNo,false);

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
    public List<List<Integer>> dealerTurn() {
        System.out.println("dealer turn");
        return deck.askDealerToDraw();
    }
}
