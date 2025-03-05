
package com.greeting.app.Greeting_App.service;

import com.greeting.app.Greeting_App.dto.AuthUserDTO;
import com.greeting.app.Greeting_App.dto.LoginDTO;
import com.greeting.app.Greeting_App.model.AuthUser;
import com.greeting.app.Greeting_App.repository.AuthUserRepository;
import com.greeting.app.Greeting_App.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private EmailService emailService;

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationService(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String registerUser(AuthUserDTO authUserDTO) {
        if (authUserRepository.findByEmail(authUserDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        AuthUser user = new AuthUser();
        user.setFirstName(authUserDTO.getFirstName());
        user.setLastName(authUserDTO.getLastName());
        user.setEmail(authUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(authUserDTO.getPassword()));

        authUserRepository.save(user);
        return "Registration successful" +  jwtUtil.generateToken(user.getEmail());
    }

    public String loginUser(LoginDTO loginDTO) {
        Optional<AuthUser> userOptional = authUserRepository.findByEmail(loginDTO.getEmail());

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found!");
        }

        if (!passwordEncoder.matches(loginDTO.getPassword(), userOptional.get().getPassword())) {
            throw new RuntimeException("Invalid email or password!");
        }
        sendLoginNotification(userOptional.get().getEmail());
        String token=jwtUtil.generateToken(userOptional.get().getEmail());
        return "Login successful, token: "+token;
    }



    // New method to send email notification
    private void sendLoginNotification(String email) {
        emailService.sendSimpleEmail(email, "Login Alert", "You have successfully logged into your account.");
    }

}
