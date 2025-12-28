
package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Roll number is required") // Validation added
    private String rollNumber;

    @NotBlank(message = "Name is required") // Validation added
    private String name;

    private String department;
    private Integer year;
}