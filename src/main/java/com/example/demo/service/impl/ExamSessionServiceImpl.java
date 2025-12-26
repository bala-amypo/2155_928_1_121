package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ExamSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamSessionServiceImpl implements ExamSessionService {
    private final ExamSessionRepository sessionRepo;
    private final StudentRepository studentRepo;

    @Override
    public ExamSession createSession(ExamSession session) {
        if (session.getExamDate() != null && session.getExamDate().isBefore(LocalDate.now())) {
            throw new ApiException("Exam date cannot be in the past");
        }
        if (session.getStudents() == null || session.getStudents().isEmpty()) {
            throw new ApiException("Session must have at least 1 student");
        }
        return sessionRepo.save(session);
    }

    @Override
    public ExamSession getSession(Long id) {
        return sessionRepo.findById(id).orElseThrow(() -> new ApiException("Session not found"));
    }
}