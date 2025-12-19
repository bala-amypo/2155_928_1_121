package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public User register(User user) {
        if (repo.findByEmail(user.getEmail()).isPresent())
            throw new ApiException("exists");

        if (user.getRole() == null)
            user.setRole("STAFF");

        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public User findByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new ApiException("not found"));
    }
}
