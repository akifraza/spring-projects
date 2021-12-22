package com.myapp.secureapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping ("/public/home")
    public String home() {
        return "This is Home Page";
    }


    @GetMapping ("/public/login")
    public String login() {
        return "This is Login Page";
    }
    
    @GetMapping ("/public/test")
    public String test() {
        return "This is Test Page";
    }
}
