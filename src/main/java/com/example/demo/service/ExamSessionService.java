package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class ExamSessionService {

    private final ExamSessionRepository examSessionRepository;
    private final StudentRepository studentRepository;

    public ExamSessionService(ExamSessionRepository examSessionRepository,
                              StudentRepository studentRepository) {
        this.examSessionRepository = examSessionRepository;
        this.studentRepository = studentRepository;
    }

    public ExamSession createSession(ExamSession session) {
        if (session.getExamDate().isBefore(LocalDate.now())) {
            throw new ApiException("past");
        }
        if (session.getStudents() == null || session.getStudents().isEmpty()) {
            throw new ApiException("at least 1 student");
        }
        return examSessionRepository.save(session);
    }

    public ExamSession getSession(Long id) {
        return examSessionRepository.findById(id)
                .orElseThrow(() -> new ApiException("session not found"));
    }
}
