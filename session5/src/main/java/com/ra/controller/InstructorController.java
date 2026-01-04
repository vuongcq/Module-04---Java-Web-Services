package com.ra.controller;

import com.ra.model.dto.instructor.InstructorRequestDTO;
import com.ra.model.dto.instructor.InstructorResponseDTO;
import com.ra.service.imp.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("instructors")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    @GetMapping
    public ResponseEntity<List<InstructorResponseDTO>> getAllInstructors() {
        return ResponseEntity.ok().body(instructorService.getAllInstructors());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addInstructor(@RequestBody InstructorRequestDTO instructorRequestDTO) {
        return ResponseEntity.ok().body(instructorService.saveInstructor(instructorRequestDTO));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<InstructorResponseDTO> updateInstructor(@RequestBody InstructorRequestDTO instructorRequestDTO, @PathVariable Long id) {
        return ResponseEntity.ok().body(instructorService.updateInstructor(instructorRequestDTO,id));
    }

    // xoa giang vien
    @DeleteMapping("/{id}")
    public void deleteInstructor(@PathVariable long id) {
        instructorService.deleteInstructor(id);   }
}
