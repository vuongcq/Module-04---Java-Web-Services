package com.ra.controller;


import com.ra.model.dto.department.DepartmentRequestDTO;
import com.ra.model.dto.department.DepartmentResponseDTO;
import com.ra.model.entity.Department;
import com.ra.service.imp.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

// get toàn bộ danh sách
    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartment(){
        return  ResponseEntity.ok().body(departmentService.getAllDepartment());
    }

    // thêm mới gửi lên server chứ k có getForm (dùng @GetMapping)
    @PostMapping("/add")
    public ResponseEntity<DepartmentResponseDTO> addDepartment(@RequestBody DepartmentRequestDTO departmentRequestDTO){
        return ResponseEntity.ok().body(departmentService.saveDepartment(departmentRequestDTO));
    }

    // cap nhat
    @PutMapping("/edit/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment
    (@RequestBody DepartmentRequestDTO departmentRequestDTO, @PathVariable Long id){
        return ResponseEntity.ok().body(departmentService.updateDepartment(departmentRequestDTO,id));
    }
    // xoa
    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
    }



}
