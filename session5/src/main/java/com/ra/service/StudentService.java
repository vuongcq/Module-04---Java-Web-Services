package com.ra.service;

import com.ra.model.dto.student.StudentResponseDTO;
import com.ra.model.dto.student.StudentResquestDTO;
import com.ra.model.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    List<StudentResponseDTO> getStudents();
    StudentResponseDTO addStudent(StudentResquestDTO studentResquestDTO);
    StudentResponseDTO updateStudent(StudentResquestDTO studentResquestDTO, Long id);
    void deleteStudent(Long id);
    StudentResponseDTO findStudentById(Long id);
}
