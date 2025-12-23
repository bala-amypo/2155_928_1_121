package com.example.demo.model;

import com.example.demo.exception.ApiException;
import jakarta.persistence.*;

@Entity
@Table(name = "exam_rooms")
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String roomNumber;

    private Integer capacity;
    private Integer rows;
    private Integer columns;

    @PrePersist
    @PreUpdate
    public void ensureCapacityMatches() {
        if (rows <= 0 || columns <= 0) {
            throw new ApiException("invalid rows or columns");
        }
        this.capacity = rows * columns;
    }

    // getters & setters
}
