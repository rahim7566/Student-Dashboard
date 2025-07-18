package com.student.dashboard.dto;

import lombok.Data;

@Data
public class GradeDTO {
    private Long id;
    private String grade;
    private EnrollmentDTO enrollmentDTO;
}
