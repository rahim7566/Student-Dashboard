package com.student.dashboard.service;

import com.student.dashboard.configuration.ResourceNotFoundException;
import com.student.dashboard.model.Enrollment;
import com.student.dashboard.model.Grade;
import com.student.dashboard.repository.EnrollmentRepository;
import com.student.dashboard.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private final EnrollmentRepository enrollmentRepository;
    private final GradeRepository gradeRepository;

    private static final Pattern GRADE_PATTERN = Pattern.compile("A|B|C|D|F");

    @Override
    public void recordGrade(Long studentId, Long courseId, String grade) {
        if (!GRADE_PATTERN.matcher(grade).matches()) {
            throw new IllegalArgumentException("Invalid grade. Allowed values are A, B, C, D, F.");
        }

        Enrollment enrollment = enrollmentRepository
                .findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Student is not enrolled in the course."));

        if (enrollment.isCompleted()) {
            throw new IllegalStateException("Cannot update grade. Course is already completed.");
        }

        Grade gradeEntity = enrollment.getGrade();
        if (gradeEntity == null) {
            gradeEntity = new Grade();
            gradeEntity.setEnrollment(enrollment);
        }

        gradeEntity.setGrade(grade);
        enrollment.setCompleted(true);
        gradeRepository.save(gradeEntity);
    }
}