package com.greeting.app.Greeting_App.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public static String getGreetingMessage() {
        return "Hello World";
    }
}
