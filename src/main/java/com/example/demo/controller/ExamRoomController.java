package com.example.demo.controller;

import com.example.demo.model.ExamRoom;
import com.example.demo.service.ExamRoomService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class ExamRoomController {

    private final ExamRoomService service;

    public ExamRoomController(ExamRoomService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Add an exam room")
    public ExamRoom addRoom(@RequestBody ExamRoom room) {
        return service.addRoom(room);
    }

    @GetMapping
    @Operation(summary = "Get all exam rooms")
    public List<ExamRoom> getAllRooms() {
        return service.getAllRooms();
    }
}
