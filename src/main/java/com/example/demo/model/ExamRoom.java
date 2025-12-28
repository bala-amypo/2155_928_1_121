
package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ExamRoom {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String roomNumber;

    // FIX: 'rows' is a reserved keyword in MySQL, so we map it to 'room_rows'
    @Column(name = "room_rows") 
    private Integer rows;

    // Best practice to rename 'columns' as well to avoid conflicts
    @Column(name = "room_columns")
    private Integer columns;

    private Integer capacity;

    public void ensureCapacityMatches() {
        if (this.rows != null && this.columns != null) {
            this.capacity = this.rows * this.columns;
        }
    }
}