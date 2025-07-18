package com.student.dashboard.controller;

import com.student.dashboard.dto.EnrollmentDTO;
import com.student.dashboard.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enrollment")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/{studentId}/{courseIds}")
    public ResponseEntity<List<EnrollmentDTO>> enrollStudent(@PathVariable Long studentId, @PathVariable List<Long> courseIds) {
        List<EnrollmentDTO> created = enrollmentService.enrollStudent(studentId, courseIds);
        return ResponseEntity.ok(created);
    }
}
