// package com.example.demo.service;

// import com.example.demo.exception.ApiException;
// import com.example.demo.model.Student;
// import com.example.demo.repository.StudentRepository;
// import org.springframework.stereotype.Service;
// import java.util.List;

// @Service
// public class StudentService {

//     private final StudentRepository studentRepository;

//     public StudentService(StudentRepository studentRepository) {
//         this.studentRepository = studentRepository;
//     }

//     public Student addStudent(Student student) {
//         if (student.getYear() < 1 || student.getYear() > 5) {
//             throw new ApiException("year");
//         }
//         if (studentRepository.findByRollNumber(student.getRollNumber()).isPresent()) {
//             throw new ApiException("exists");
//         }
//         return studentRepository.save(student);
//     }

//     public List<Student> getAllStudents() {
//         return studentRepository.findAll();
//     }
// }
package com.example.demo.service;

import com.example.demo.model.Student;
import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    List<Student> getAllStudents();
}