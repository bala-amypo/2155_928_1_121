package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.service.ExamRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamRoomServiceImpl implements ExamRoomService {
    private final ExamRoomRepository roomRepo;

    @Override
    public ExamRoom addRoom(ExamRoom room) {
        if (room.getRows() != null && room.getRows() < 0) throw new ApiException("Rows cannot be negative");
        if (roomRepo.findByRoomNumber(room.getRoomNumber()).isPresent()) {
            throw new ApiException("Room number exists");
        }
        room.ensureCapacityMatches();
        return roomRepo.save(room);
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return roomRepo.findAll();
    }
}