package com.ra.service.imp;

import com.ra.model.dto.department.DepartmentResponseDTO;
import com.ra.model.dto.instructor.InstructorRequestDTO;
import com.ra.model.dto.instructor.InstructorResponseDTO;
import com.ra.model.entity.Department;
import com.ra.model.entity.Instructor;
import com.ra.repository.DepartmentRepository;
import com.ra.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService implements com.ra.service.InstructorService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    public List<InstructorResponseDTO> getAllInstructors() {
        return instructorRepository.findAll().stream().map(
                instructor -> InstructorResponseDTO.builder()
                        .id(instructor.getId())
                        .name(instructor.getName())
                        .email(instructor.getEmail())
                        .departmentId(instructor.getDepartment().getId())
                        .departmentName(instructor.getDepartment().getDepartmentName()).build()
        ).toList();

    }

    @Override
    public InstructorResponseDTO saveInstructor(InstructorRequestDTO instructorRequestDTO) {
        Instructor instructor = Instructor.builder()
                .name(instructorRequestDTO.getName())
                .email(instructorRequestDTO.getEmail())
                .department(departmentRepository.findById(instructorRequestDTO.getDepartmentId()).orElse(null)).build(); //
        // cho nay can luu y


        Instructor savedInstructor = instructorRepository.save(instructor);

        return InstructorResponseDTO.builder()
                .id(savedInstructor.getId())
                .name(savedInstructor.getName())
                .email(savedInstructor.getEmail())
                .departmentId(savedInstructor.getDepartment().getId())
                .departmentName(savedInstructor.getDepartment().getDepartmentName()).build();
    }

    @Override
    public InstructorResponseDTO updateInstructor(InstructorRequestDTO instructorRequestDTO, Long id) {
        Instructor instructor = Instructor.builder()
                .id(id)
                .name(instructorRequestDTO.getName())
                .email(instructorRequestDTO.getEmail())
                .department(departmentRepository.findById(instructorRequestDTO.getDepartmentId()).orElse(null)).build();

        Instructor updatedInstructor = instructorRepository.save(instructor);

        return InstructorResponseDTO.builder()
                .id(updatedInstructor.getId())
                .name(updatedInstructor.getName())
                .email(updatedInstructor.getEmail())
                .departmentId(updatedInstructor.getDepartment().getId())
                .departmentName(updatedInstructor.getDepartment().getDepartmentName()).build();
    }

    @Override
    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }

    @Override
    public InstructorResponseDTO findInstructorById(Long id) {
        return null;
    }
}
