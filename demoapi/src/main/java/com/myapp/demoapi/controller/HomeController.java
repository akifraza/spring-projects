package com.myapp.demoapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping ("/home")
    public String getHome () {
        return "Hello Home";
    }

    @GetMapping ("/user")
    public String getUser () {
        return "Hello User";
    }
}
