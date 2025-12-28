
package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    // Rename 'accessToken' to 'token' so Lombok generates 'getToken()'
    private String token; 
    private String tokenType;
}