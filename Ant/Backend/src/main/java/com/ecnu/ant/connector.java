package com.ecnu.ant;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class connector {

    @PostMapping(value = "test")
    public String getTest() {
        System.out.println("get post");
        return "hello test";
    }

    @PostMapping(value = "getGameData")
    public String getGameData() {
        System.out.println("sending data");
        return "hello test";
    }

    @PostMapping(value = "getPosition")
    public String getPosition() {
        System.out.println("sending position");
        return "hello position";
    }
}
