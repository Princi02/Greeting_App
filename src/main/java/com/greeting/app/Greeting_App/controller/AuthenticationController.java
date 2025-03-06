package com.greeting.app.Greeting_App.controller;

import com.greeting.app.Greeting_App.dto.AuthUserDTO;
import com.greeting.app.Greeting_App.dto.LoginDTO;
import com.greeting.app.Greeting_App.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import java.util.Map;

@RestController
@RequestMapping(value ="/auth")
@Tag(name = "Authentication API", description = "User Registration & Login APIs")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Registers a user with email and password")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AuthUserDTO authUserDTO) {
        return ResponseEntity.ok(authenticationService.registerUser(authUserDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "User Login", description = "Logs in a user and returns a JWT token")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(authenticationService.loginUser(loginDTO));
    }

    @PutMapping("/forgotPassword/{email}")
    public ResponseEntity<?> forgotPassword(@PathVariable String email, @RequestBody Map<String, String> request) {
        String newPassword = request.get("password");
        return ResponseEntity.ok(authenticationService.forgotPassword(email, newPassword));
    }

    @PutMapping("/resetPassword/{email}")
    public ResponseEntity<?> resetPassword(@PathVariable String email, @RequestBody Map<String, String> request) {
        String currentPassword = request.get("currentPassword");
        String newPassword = request.get("newPassword");
        return ResponseEntity.ok(authenticationService.resetPassword(email, currentPassword, newPassword));
    }

}
