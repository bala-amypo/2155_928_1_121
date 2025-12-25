package com.example.demo.service;

import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamRoomService {

    private final ExamRoomRepository repository;

    public ExamRoomService(ExamRoomRepository repository) {
        this.repository = repository;
    }

    public ExamRoom save(ExamRoom room) {
        return repository.save(room);
    }

    public List<ExamRoom> findAll() {
        return repository.findAll();
    }
}
