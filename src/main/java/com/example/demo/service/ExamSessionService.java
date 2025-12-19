package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class ExamSessionService {

    private final ExamSessionRepository repo;

    public ExamSessionService(ExamSessionRepository repo) {
        this.repo = repo;
    }

    public ExamSession createSession(ExamSession s) {
        if (s.getExamDate().isBefore(LocalDate.now()))
            throw new ApiException("past");

        if (s.getStudents() == null || s.getStudents().isEmpty())
            throw new ApiException("at least 1 student");

        return repo.save(s);
    }

    public ExamSession getSession(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ApiException("session not found"));
    }
}
