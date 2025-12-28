
package com.example.demo.controller;

import com.example.demo.model.ExamRoom;
import com.example.demo.service.ExamRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
@Tag(name = "Rooms", description = "Exam Room Management")
public class ExamRoomController {
    private final ExamRoomService service;

    @PostMapping
    @Operation(summary = "Add a new exam room")
    public ResponseEntity<ExamRoom> add(@RequestBody ExamRoom r) {
        return ResponseEntity.ok(service.addRoom(r));
    }

    @GetMapping
    @Operation(summary = "List all rooms")
    public ResponseEntity<List<ExamRoom>> list() {
        return ResponseEntity.ok(service.getAllRooms());
    }
}