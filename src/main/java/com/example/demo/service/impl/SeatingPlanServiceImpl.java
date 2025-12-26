package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.SeatingPlanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SeatingPlanServiceImpl implements SeatingPlanService {
    private final ExamSessionRepository sessionRepo;
    private final SeatingPlanRepository planRepo;
    private final ExamRoomRepository roomRepo;

    @Override
    public SeatingPlan generatePlan(Long sessionId) {
        ExamSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ApiException("Session not found"));

        int requiredCapacity = session.getStudents().size();
        
        List<ExamRoom> rooms = roomRepo.findAll();
        ExamRoom selectedRoom = rooms.stream()
                .filter(r -> r.getCapacity() >= requiredCapacity)
                .findFirst()
                .orElseThrow(() -> new ApiException("No room found with sufficient capacity"));

        // Generate JSON Arrangement (Simple Mock Logic)
        Map<String, String> arrangement = new HashMap<>();
        List<Student> students = new ArrayList<>(session.getStudents());
        for (int i = 0; i < students.size(); i++) {
            arrangement.put("Seat-" + (i + 1), students.get(i).getRollNumber());
        }

        String json = "{}";
        try { json = new ObjectMapper().writeValueAsString(arrangement); } catch (Exception e) {}

        SeatingPlan plan = SeatingPlan.builder()
                .examSession(session)
                .room(selectedRoom)
                .arrangementJson(json)
                .generatedAt(LocalDateTime.now())
                .build();

        return planRepo.save(plan);
    }

    @Override
    public SeatingPlan getPlan(Long id) {
        return planRepo.findById(id).orElseThrow(() -> new ApiException("Plan not found"));
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return planRepo.findByExamSessionId(sessionId);
    }
}