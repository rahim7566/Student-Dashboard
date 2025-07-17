package com.student.dashboard.service;

import com.student.dashboard.configuration.ResourceNotFoundException;
import com.student.dashboard.model.Course;
import com.student.dashboard.model.Enrollment;
import com.student.dashboard.model.Student;
import com.student.dashboard.repository.CourseRepository;
import com.student.dashboard.repository.EnrollmentRepository;
import com.student.dashboard.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public void enrollStudent(Long studentId, List<Long> courseIds) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        List<Course> courses = courseRepository.findAllById(courseIds);
        if (courses.size() != courseIds.size()) {
            throw new ResourceNotFoundException("One or more courses not found");
        }

        Set<Long> alreadyEnrolledCourseIds = student.getEnrollments().stream()
                .map(e -> e.getCourse().getId())
                .collect(Collectors.toSet());

        List<Enrollment> enrollmentsToSave = new ArrayList<>();

        for (Course course : courses) {
            if (alreadyEnrolledCourseIds.contains(course.getId())) {
                continue;
            }
            boolean prerequisitesMet = course.getPrerequisites() == null || course.getPrerequisites().stream()
                    .allMatch(prereq ->
                            student.getEnrollments().stream()
                                    .anyMatch(e -> e.getCourse().getId().equals(prereq.getId()) && e.isCompleted())
                    );

            if (!prerequisitesMet) {
                throw new IllegalArgumentException("Student does not meet prerequisites for course: " + course.getTitle());
            }
            long currentEnrollment = course.getEnrollments().size();
            if (currentEnrollment >= course.getCapacity()) {
                throw new IllegalStateException("Course is full: " + course.getTitle());
            }

            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            enrollment.setCompleted(false);

            enrollmentsToSave.add(enrollment);
        }

        enrollmentRepository.saveAll(enrollmentsToSave);
    }
}