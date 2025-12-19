package com.example.demo.controller;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sessions")
public class ExamSessionController {

    private final ExamSessionService service;

    public ExamSessionController(ExamSessionService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create exam session")
    public ExamSession createSession(@RequestBody ExamSession session) {
        return service.createSession(session);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get exam session by ID")
    public ExamSession getSession(@PathVariable Long id) {
        return service.getSession(id);
    }
}
