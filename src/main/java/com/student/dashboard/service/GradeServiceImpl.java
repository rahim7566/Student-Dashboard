package com.student.dashboard.service;

import com.student.dashboard.dto.GradeDTO;
import com.student.dashboard.model.Enrollment;
import com.student.dashboard.model.Grade;
import com.student.dashboard.repository.EnrollmentRepository;
import com.student.dashboard.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

    private static final Pattern GRADE_PATTERN = Pattern.compile("A|B|C|D|F");
    private final EnrollmentRepository enrollmentRepository;
    private final GradeRepository gradeRepository;
    private final ModelMapper modelMapper;

    @Override
    public GradeDTO recordGrade(Long studentId, Long courseId, String grade) {
        if (!GRADE_PATTERN.matcher(grade).matches()) {
            throw new IllegalArgumentException("Invalid grade. Allowed values are A, B, C, D, F.");
        }

        Enrollment enrollment = enrollmentRepository
                .findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new NoSuchElementException("Student is not enrolled in the course."));

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
        Grade saved = gradeRepository.save(gradeEntity);
        return mapToDTO(saved);
    }

    private GradeDTO mapToDTO(Grade saved) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<Grade, GradeDTO> typeMap = modelMapper.createTypeMap(Grade.class, GradeDTO.class);
        typeMap.addMappings(mapper -> mapper.map(Grade::getEnrollment, GradeDTO::setEnrollmentDTO));
        return modelMapper.map(saved, GradeDTO.class);
    }
}