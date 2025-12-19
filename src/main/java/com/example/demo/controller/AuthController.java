package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwtProvider;
    private final UserRepository userRepository;

    public AuthController(UserService userService,
                          AuthenticationManager authManager,
                          JwtTokenProvider jwtProvider,
                          UserRepository userRepository) {
        this.userService = userService;
        this.authManager = authManager;
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    @Operation(summary = "Register new user")
    public User register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setName(request.name);
        user.setEmail(request.email);
        user.setPassword(request.password);
        user.setRole(request.role);
        return userService.register(user);
    }

    @PostMapping("/login")
    @Operation(summary = "Login and get JWT token")
    public AuthResponse login(@RequestBody AuthRequest request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email,
                        request.password
                )
        );

        User user = userRepository.findByEmail(request.email).get();

        String token = jwtProvider.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        AuthResponse response = new AuthResponse();
        response.token = token;
        response.userId = user.getId();
        response.email = user.getEmail();
        response.role = user.getRole();

        return response;
    }
}
