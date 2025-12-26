// package com.example.demo.service;

// import com.example.demo.exception.ApiException;
// import com.example.demo.model.*;
// import com.example.demo.repository.*;
// import org.springframework.stereotype.Service;
// import java.util.List;

// @Service
// public class SeatingPlanService {

//     private final ExamSessionRepository examSessionRepository;
//     private final SeatingPlanRepository seatingPlanRepository;
//     private final ExamRoomRepository examRoomRepository;

//     public SeatingPlanService(ExamSessionRepository examSessionRepository,
//                               SeatingPlanRepository seatingPlanRepository,
//                               ExamRoomRepository examRoomRepository) {
//         this.examSessionRepository = examSessionRepository;
//         this.seatingPlanRepository = seatingPlanRepository;
//         this.examRoomRepository = examRoomRepository;
//     }

//     public SeatingPlan generatePlan(Long sessionId) {
//         ExamSession session = examSessionRepository.findById(sessionId)
//                 .orElseThrow(() -> new ApiException("session not found"));

//         int required = session.getStudents().size();
//         ExamRoom room = examRoomRepository.findByCapacityGreaterThanEqual(required)
//                 .stream()
//                 .findFirst()
//                 .orElseThrow(() -> new ApiException("no room"));

//         StringBuilder json = new StringBuilder("{");
//         int seat = 1;
//         for (Student s : session.getStudents()) {
//             json.append("\"seat").append(seat++).append("\":\"")
//                 .append(s.getRollNumber()).append("\",");
//         }
//         json.deleteCharAt(json.length() - 1).append("}");

//         SeatingPlan plan = new SeatingPlan();
//         plan.setExamSession(session);
//         plan.setRoom(room);
//         plan.setArrangementJson(json.toString());

//         return seatingPlanRepository.save(plan);
//     }

//     public SeatingPlan getPlan(Long id) {
//         return seatingPlanRepository.findById(id)
//                 .orElseThrow(() -> new ApiException("plan not found"));
//     }

//     public List<SeatingPlan> getPlansBySession(Long sessionId) {
//         return seatingPlanRepository.findByExamSessionId(sessionId);
//     }
// }
package com.example.demo.service;

import com.example.demo.model.SeatingPlan;
import java.util.List;

public interface SeatingPlanService {
    SeatingPlan generatePlan(Long sessionId);
    SeatingPlan getPlan(Long id);
    List<SeatingPlan> getPlansBySession(Long sessionId);
}