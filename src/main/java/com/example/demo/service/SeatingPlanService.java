package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SeatingPlanService {

    private final ExamSessionRepository sessionRepo;
    private final SeatingPlanRepository planRepo;
    private final ExamRoomRepository roomRepo;

    public SeatingPlanService(ExamSessionRepository s,
                              SeatingPlanRepository p,
                              ExamRoomRepository r) {
        this.sessionRepo = s;
        this.planRepo = p;
        this.roomRepo = r;
    }

    public SeatingPlan generatePlan(Long sessionId) {
        ExamSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ApiException("session not found"));

        int count = session.getStudents().size();

        ExamRoom room = roomRepo.findByCapacityGreaterThanEqual(count)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ApiException("no room"));

        Map<String, String> map = new LinkedHashMap<>();
        int seat = 1;
        for (Student s : session.getStudents()) {
            map.put("Seat-" + seat++, s.getRollNumber());
        }

        try {
            String json = new ObjectMapper().writeValueAsString(map);

            SeatingPlan plan = new SeatingPlan();
            plan.setExamSession(session);
            plan.setRoom(room);
            plan.setArrangementJson(json);

            return planRepo.save(plan);
        } catch (Exception e) {
            throw new ApiException("json");
        }
    }

    public SeatingPlan getPlan(Long id) {
        return planRepo.findById(id)
                .orElseThrow(() -> new ApiException("plan not found"));
    }

    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return planRepo.findByExamSessionId(sessionId);
    }
}
