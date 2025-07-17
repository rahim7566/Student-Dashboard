package com.student.dashboard.service;

import com.student.dashboard.configuration.ResourceNotFoundException;
import com.student.dashboard.dto.CourseDTO;
import com.student.dashboard.model.Course;
import com.student.dashboard.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Override
    public CourseDTO createCourse(CourseDTO dto) {
        Course course = modelMapper.map(dto, Course.class);
        if (dto.getPrerequisiteIds() != null) {
            List<Course> prerequisites = courseRepository.findAllById(dto.getPrerequisiteIds());
            course.setPrerequisites(prerequisites);
        }
        Course saved = courseRepository.save(course);
        return modelMapper.map(saved, CourseDTO.class);
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        return mapToDto(course);
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseDTO dto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        course.setTitle(dto.getTitle());
        course.setCapacity(dto.getCapacity());

        if (dto.getPrerequisiteIds() != null) {
            List<Course> prerequisites = courseRepository.findAllById(dto.getPrerequisiteIds());
            course.setPrerequisites(prerequisites);
        }

        Course updated = courseRepository.save(course);
        return mapToDto(updated);
    }

    @Override
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found");
        }
        courseRepository.deleteById(id);
    }

    @Override
    public Page<CourseDTO> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable)
                .map(this::mapToDto);
    }

    private CourseDTO mapToDto(Course course) {
        CourseDTO dto = modelMapper.map(course, CourseDTO.class);
        if (course.getPrerequisites() != null) {
            dto.setPrerequisiteIds(
                    course.getPrerequisites().stream()
                            .map(Course::getId)
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }
}