package com.student.dashboard.service;

import java.util.List;

public interface EnrollmentService {
    void enrollStudent(Long studentId, List<Long> courseIds);
}