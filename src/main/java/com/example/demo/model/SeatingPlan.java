package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "seating_plans")
public class SeatingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exam_session_id")
    private ExamSession examSession;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private ExamRoom room;

    @Column(columnDefinition = "TEXT")
    private String arrangementJson;

    private LocalDateTime generatedAt;

    @PrePersist
    void onCreate() {
        if (generatedAt == null) {
            generatedAt = LocalDateTime.now();
        }
    }

    // ✅ GETTERS
    public Long getId() {
        return id;
    }

    public ExamSession getExamSession() {
        return examSession;
    }

    public ExamRoom getRoom() {
        return room;
    }

    public String getArrangementJson() {
        return arrangementJson;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    // ✅ SETTERS (THIS FIXES THE ERROR)
    public void setId(Long id) {
        this.id = id;
    }

    public void setExamSession(ExamSession examSession) {
        this.examSession = examSession;
    }

    public void setRoom(ExamRoom room) {
        this.room = room;
    }

    public void setArrangementJson(String arrangementJson) {
        this.arrangementJson = arrangementJson;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}
