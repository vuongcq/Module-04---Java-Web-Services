package com.ra.service;

import com.ra.model.dto.instructor.InstructorRequestDTO;
import com.ra.model.dto.instructor.InstructorResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstructorService {
    List<InstructorResponseDTO> getAllInstructors();
    InstructorResponseDTO saveInstructor(InstructorRequestDTO instructorRequestDTO);
    InstructorResponseDTO updateInstructor(InstructorRequestDTO instructorRequestDTO, Long id);
    void deleteInstructor(Long id);
    InstructorResponseDTO findInstructorById(Long id);
}
