package com.student.dashboard.service;

import com.student.dashboard.configuration.ResourceNotFoundException;
import com.student.dashboard.dto.StudentDTO;
import com.student.dashboard.model.Student;
import com.student.dashboard.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<StudentDTO> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable)
                .map(student -> modelMapper.map(student, StudentDTO.class));
    }

    @Override
    public StudentDTO createStudent(StudentDTO dto) {
        Student student = modelMapper.map(dto, Student.class);
        Student saved = studentRepository.save(student);
        return modelMapper.map(saved, StudentDTO.class);
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());

        Student updated = studentRepository.save(student);
        return modelMapper.map(updated, StudentDTO.class);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new NoSuchElementException("Student not found");
        }
        studentRepository.deleteById(id);
    }
}