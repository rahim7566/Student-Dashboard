package com.student.dashboard.service;

public interface GradeService {
    void recordGrade(Long studentId, Long courseId, String grade);
}