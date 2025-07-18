package com.student.dashboard.service;

import com.student.dashboard.dto.GradeDTO;

public interface GradeService {
    GradeDTO recordGrade(Long studentId, Long courseId, String grade);
}