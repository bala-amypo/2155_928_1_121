// package com.example.demo.model;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "exam_rooms")
// public class ExamRoom {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String roomNumber;
//     private Integer capacity;
//     private Integer rows;
//     private Integer columns;

//     public Long getId() {
//         return id;
//     }

//     public String getRoomNumber() {
//         return roomNumber;
//     }

//     public void setRoomNumber(String roomNumber) {
//         this.roomNumber = roomNumber;
//     }

//     public Integer getCapacity() {
//         return capacity;
//     }

//     public void setCapacity(Integer capacity) {
//         this.capacity = capacity;
//     }

//     public Integer getRows() {
//         return rows;
//     }

//     public void setRows(Integer rows) {
//         this.rows = rows;
//     }

//     public Integer getColumns() {
//         return columns;
//     }

//     public void setColumns(Integer columns) {
//         this.columns = columns;
//     }
// }
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