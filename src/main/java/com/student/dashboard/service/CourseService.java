package com.student.dashboard.service;

import com.student.dashboard.dto.CourseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    CourseDTO createCourse(CourseDTO dto);

    CourseDTO getCourseById(Long id);

    CourseDTO updateCourse(Long id, CourseDTO dto);

    void deleteCourse(Long id);

    Page<CourseDTO> getAllCourses(Pageable pageable);
}
