package com.ballys.poc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    private String helloWorld(){
        return "HelloWorld";
    }
}
