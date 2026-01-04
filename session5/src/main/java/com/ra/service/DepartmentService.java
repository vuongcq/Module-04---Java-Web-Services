package com.ra.service;

import com.ra.model.dto.department.DepartmentRequestDTO;
import com.ra.model.dto.department.DepartmentResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    public List<DepartmentResponseDTO> getAllDepartment();
    public DepartmentResponseDTO saveDepartment(DepartmentRequestDTO department);
    public DepartmentResponseDTO updateDepartment(DepartmentRequestDTO department, Long id);
    public void deleteDepartment(Long id);
    public DepartmentResponseDTO findDepartmentById(Long id);
}
