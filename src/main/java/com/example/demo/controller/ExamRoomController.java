package com.example.demo.controller;

import com.example.demo.model.ExamRoom;
import com.example.demo.service.RoomService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class ExamRoomController {

    private final RoomService roomService;

    public ExamRoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ExamRoom addRoom(@RequestBody ExamRoom room) {
        return roomService.save(room);
    }

    @GetMapping
    public List<ExamRoom> getRooms() {
        return roomService.findAll();
    }
}
