package com.student.dashboard.dto;

import lombok.Data;

@Data
public class EnrollmentDTO {
    private Long id;
    private Boolean isCompleted;
    private StudentDTO studentDTO;
    private CourseDTO courseDTO;
}
