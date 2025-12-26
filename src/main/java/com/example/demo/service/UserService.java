// package com.example.demo.service;

// import com.example.demo.exception.ApiException;
// import com.example.demo.model.User;
// import com.example.demo.repository.UserRepository;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// @Service
// public class UserService {

//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;

//     public UserService(UserRepository userRepository,
//                        PasswordEncoder passwordEncoder) {
//         this.userRepository = userRepository;
//         this.passwordEncoder = passwordEncoder;
//     }

//     public User register(User user) {
//         if (userRepository.findByEmail(user.getEmail()).isPresent()) {
//             throw new ApiException("exists");
//         }
//         if (user.getRole() == null) user.setRole("STAFF");
//         user.setPassword(passwordEncoder.encode(user.getPassword()));
//         return userRepository.save(user);
//     }

//     public User findByEmail(String email) {
//         return userRepository.findByEmail(email)
//                 .orElseThrow(() -> new ApiException("not found"));
//     }
// }
package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User register(User user);
    User findByEmail(String email);
}