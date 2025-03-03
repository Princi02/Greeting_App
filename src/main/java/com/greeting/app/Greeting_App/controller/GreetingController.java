package com.greeting.app.Greeting_App.controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/greeting")
public class GreetingController {
    @GetMapping
    public String getGreeting() {
        return "Hello, this is a GET request!";
    }
}