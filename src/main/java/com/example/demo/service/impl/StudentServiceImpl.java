package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepo;

    @Override
    public Student addStudent(Student student) {
        // FIX: Explicit logic check for service-layer tests
        if (student.getRollNumber() == null || student.getRollNumber().trim().isEmpty() ||
            student.getName() == null || student.getName().trim().isEmpty()) {
            throw new ApiException("Roll number and Name are required");
        }

        if (student.getYear() != null && student.getYear() > 5) {
            throw new ApiException("Invalid year");
        }
        if (studentRepo.findByRollNumber(student.getRollNumber()).isPresent()) {
            throw new ApiException("Student with this roll number exists");
        }
        return studentRepo.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }
}