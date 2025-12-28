
package com.example.demo.controller;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
@Tag(name = "Sessions", description = "Exam Session Management")
public class ExamSessionController {
    private final ExamSessionService service;

    @PostMapping
    @Operation(summary = "Create a new exam session")
    public ResponseEntity<ExamSession> create(@RequestBody ExamSession s) {
        return ResponseEntity.ok(service.createSession(s));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get exam session details by ID")
    public ResponseEntity<ExamSession> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSession(id));
    }
}