package com.ra.service.imp;

import com.ra.model.dto.department.DepartmentRequestDTO;
import com.ra.model.dto.department.DepartmentResponseDTO;
import com.ra.model.entity.Department;
import com.ra.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService implements com.ra.service.DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentResponseDTO> getAllDepartment() {
        return departmentRepository.findAll().stream().map(
                department -> DepartmentResponseDTO.builder()
                        .id(department.getId())
                        .departmentName(department.getDepartmentName())
                        .description(department.getDescription()).build()
        ).toList();
    }

    @Override
    public DepartmentResponseDTO saveDepartment(DepartmentRequestDTO departmentRequestDTO) {
        Department department = Department.builder()
                .departmentName(departmentRequestDTO.getDepartmentName())
                .description(departmentRequestDTO.getDescription()).build();

        Department savedDepartment = departmentRepository.save(department);
        return DepartmentResponseDTO.builder()
                .id(savedDepartment.getId())
                .departmentName(savedDepartment.getDepartmentName())
                .description(savedDepartment.getDescription()).build();
    }

    @Override
    public DepartmentResponseDTO updateDepartment(DepartmentRequestDTO departmentRequestDTO, Long id) {
        Department department = Department.builder()
                .id(id)
                .departmentName(departmentRequestDTO.getDepartmentName())
                .description(departmentRequestDTO.getDescription()).build();

        Department updatedDepartment =departmentRepository.save(department);
        return DepartmentResponseDTO.builder()
                .id(updatedDepartment.getId())
                .departmentName(updatedDepartment.getDepartmentName())
                .description(updatedDepartment.getDescription()).build();
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public DepartmentResponseDTO findDepartmentById(Long id) {
        return null;
    }
}
