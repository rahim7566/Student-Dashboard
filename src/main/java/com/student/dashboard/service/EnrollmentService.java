package com.student.dashboard.service;

import com.student.dashboard.dto.EnrollmentDTO;
import com.student.dashboard.model.Enrollment;

import java.util.List;

public interface EnrollmentService {

    List<EnrollmentDTO> enrollStudent(Long studentId, List<Long> courseIds);
}