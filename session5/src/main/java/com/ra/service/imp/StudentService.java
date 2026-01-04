package com.ra.service.imp;

import com.ra.model.dto.student.StudentResponseDTO;
import com.ra.model.dto.student.StudentResquestDTO;
import com.ra.model.entity.Student;
import com.ra.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements com.ra.service.StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentResponseDTO> getStudents() {
        return studentRepository.findAll().stream().map(
                student -> StudentResponseDTO.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .email(student.getEmail())
                        .phoneNumber(student.getPhoneNumber()).build()
        ).toList();
    }

    @Override
    public StudentResponseDTO addStudent(StudentResquestDTO studentResquestDTO) {
        return null;
    }

    @Override
    public StudentResponseDTO updateStudent(StudentResquestDTO studentResquestDTO, Long id) {
        return null;
    }

    @Override
    public void deleteStudent(Long id) {

    }

    @Override
    public StudentResponseDTO findStudentById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        return StudentResponseDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .phoneNumber(student.getPhoneNumber()).build();
    }
}
