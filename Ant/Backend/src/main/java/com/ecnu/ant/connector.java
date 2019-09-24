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
}
