package com.student.dashboard.service;

import com.student.dashboard.dto.StudentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    Page<StudentDTO> getAllStudents(Pageable pageable);
    StudentDTO createStudent(StudentDTO dto);
    StudentDTO getStudentById(Long id);
    StudentDTO updateStudent(Long id, StudentDTO dto);
    void deleteStudent(Long id);
}