package com.example.demo.model;

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

    public void ensureCapacityMatches() {
        this.capacity = this.rows * this.columns;
    }

    public Long getId() { return id; }
    public String getRoomNumber() { return roomNumber; }
    public Integer getCapacity() { return capacity; }
    public Integer getRows() { return rows; }
    public Integer getColumns() { return columns; }

    public void setId(Long id) { this.id = id; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public void setRows(Integer rows) { this.rows = rows; }
    public void setColumns(Integer columns) { this.columns = columns; }
}
