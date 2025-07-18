package com.student.dashboard.controller;

import com.student.dashboard.dto.EnrollmentDTO;
import com.student.dashboard.dto.GradeDTO;
import com.student.dashboard.service.EnrollmentService;
import com.student.dashboard.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/grade")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    @PostMapping("/{studentId}/{courseId}/{grade}")
    public ResponseEntity<GradeDTO> enrollStudent(@PathVariable Long studentId, @PathVariable Long courseId, @PathVariable String grade) {
        GradeDTO created = gradeService.recordGrade(studentId, courseId, grade);
        return ResponseEntity.ok(created);
    }
}
