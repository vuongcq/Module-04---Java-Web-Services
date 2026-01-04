package com.ra.controller;

import com.ra.model.dto.course.CourseRequestDTO;
import com.ra.model.dto.course.CourseResponseDTO;
import com.ra.model.entity.Course;
import com.ra.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;


    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        return ResponseEntity.ok().body(courseService.getCourses());
    }

    @PostMapping("/add")
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseRequestDTO courseRequestDTO) {
        return ResponseEntity.ok().body(courseService.saveCourse(courseRequestDTO));
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@RequestBody CourseRequestDTO courseRequestDTO,
                                                          @PathVariable Long id) {
        return ResponseEntity.ok().body(courseService.updateCourse(id,courseRequestDTO));
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

    @GetMapping("/id")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok().body(courseService.findCourseById(id));
    }

}
