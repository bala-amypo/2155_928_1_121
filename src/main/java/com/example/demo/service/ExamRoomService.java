package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExamRoomService {

    private final ExamRoomRepository repo;

    public ExamRoomService(ExamRoomRepository repo) {
        this.repo = repo;
    }

    public ExamRoom addRoom(ExamRoom room) {
        if (room.getRows() <= 0 || room.getColumns() <= 0)
            throw new ApiException("invalid");

        if (repo.findByRoomNumber(room.getRoomNumber()).isPresent())
            throw new ApiException("exists");

        room.ensureCapacityMatches();
        return repo.save(room);
    }

    public List<ExamRoom> getAllRooms() {
        return repo.findAll();
    }
}
