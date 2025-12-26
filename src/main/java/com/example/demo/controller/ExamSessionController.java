// package com.example.demo.controller;

// import com.example.demo.model.ExamSession;
// import com.example.demo.service.ExamSessionService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/sessions")
// @Tag(name = "Sessions")
// public class ExamSessionController {

//     private final ExamSessionService examSessionService;

//     public ExamSessionController(ExamSessionService examSessionService) {
//         this.examSessionService = examSessionService;
//     }

//     @PostMapping
//     @Operation(summary = "Create exam session")
//     public ExamSession create(@RequestBody ExamSession session) {
//         return examSessionService.createSession(session);
//     }

//     @GetMapping("/{sessionId}")
//     @Operation(summary = "Get session details")
//     public ExamSession get(@PathVariable Long sessionId) {
//         return examSessionService.getSession(sessionId);
//     }
// }
package com.example.demo.controller;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class ExamSessionController {
    private final ExamSessionService service;

    @PostMapping
    public ResponseEntity<ExamSession> create(@RequestBody ExamSession s) {
        return ResponseEntity.ok(service.createSession(s));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamSession> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSession(id));
    }
}