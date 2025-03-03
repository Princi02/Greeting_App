package com.greeting.app.Greeting_App.controller;

import com.greeting.app.Greeting_App.entity.Greeting;
import com.greeting.app.Greeting_App.repository.GreetingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    private final GreetingRepository greetingRepository;

    public GreetingController(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    // Get all greetings
    @GetMapping
    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }

    // Create a new greeting
    @PostMapping
    public Greeting createGreeting(@RequestBody Greeting greeting) {
        return greetingRepository.save(greeting);
    }

    // Get a greeting by ID
    @GetMapping("/{id}")
    public Greeting getGreetingById(@PathVariable Long id) {
        return greetingRepository.findById(id).orElse(null);
    }

    // Delete a greeting by ID
    @DeleteMapping("/{id}")
    public void deleteGreeting(@PathVariable Long id) {
        greetingRepository.deleteById(id);
    }
}
