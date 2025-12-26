// package com.example.demo.controller;

// import com.example.demo.model.SeatingPlan;
// import com.example.demo.service.SeatingPlanService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/plans")
// @Tag(name = "Plans")
// public class SeatingPlanController {

//     private final SeatingPlanService seatingPlanService;

//     public SeatingPlanController(SeatingPlanService seatingPlanService) {
//         this.seatingPlanService = seatingPlanService;
//     }

//     @PostMapping("/generate/{sessionId}")
//     @Operation(summary = "Generate seating plan")
//     public SeatingPlan generate(@PathVariable Long sessionId) {
//         return seatingPlanService.generatePlan(sessionId);
//     }

//     @GetMapping("/{planId}")
//     @Operation(summary = "Get seating plan")
//     public SeatingPlan get(@PathVariable Long planId) {
//         return seatingPlanService.getPlan(planId);
//     }

//     @GetMapping("/session/{sessionId}")
//     @Operation(summary = "List plans by session")
//     public List<SeatingPlan> listBySession(@PathVariable Long sessionId) {
//         return seatingPlanService.getPlansBySession(sessionId);
//     }
// }
package com.example.demo.controller;

import com.example.demo.model.SeatingPlan;
import com.example.demo.service.SeatingPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
@Tag(name = "Plans", description = "Seating Plan Management")
public class SeatingPlanController {
    private final SeatingPlanService service;

    @PostMapping("/generate/{sessionId}")
    @Operation(summary = "Generate a seating plan for a session")
    public ResponseEntity<SeatingPlan> generate(@PathVariable Long sessionId) {
        return ResponseEntity.ok(service.generatePlan(sessionId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a seating plan by ID")
    public ResponseEntity<SeatingPlan> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPlan(id));
    }

    @GetMapping("/session/{sessionId}")
    @Operation(summary = "List all plans for a specific session")
    public ResponseEntity<List<SeatingPlan>> list(@PathVariable Long sessionId) {
        return ResponseEntity.ok(service.getPlansBySession(sessionId));
    }
}