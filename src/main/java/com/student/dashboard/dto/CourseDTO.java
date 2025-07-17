package com.student.dashboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.List;

@Data
public class CourseDTO {
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    @PositiveOrZero(message = "Capacity must be zero or positive")
    private int capacity;

    private List<Long> prerequisiteIds;
}
