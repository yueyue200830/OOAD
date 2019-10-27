package com.ecnu.blackjack;

import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping(value = "/drawCard",method = RequestMethod.GET)
    @ResponseBody
    public int getOneCard(@RequestParam(name="playerNo") int playerNo,@RequestParam(name = "handNo") int handNo){
        System.out.println("receive " + playerNo + " " + handNo);
        int[] numberOfHand = {1,2,3};
        Deck deck = new Deck(3, numberOfHand);
        int cardNum = deck.basicDraw(1,1,true);
        return cardNum;
    }

    @RequestMapping(value = "/doubleBet",method = RequestMethod.GET)
    @ResponseBody
    public String doubleBet(@RequestParam(name = "playerNo") int playerNo,@RequestParam(name = "handNo") int handNo){
        System.out.println("receive " + playerNo + " " + handNo);
        int[] numberOfHand = {1,2,3};
        Deck deck = new Deck(3, numberOfHand);
        deck.doubleBet(1,1);
        return "Success";
    }
}
