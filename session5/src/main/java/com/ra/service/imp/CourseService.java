package com.ra.service.imp;

import com.ra.model.dto.course.CourseRequestDTO;
import com.ra.model.dto.course.CourseResponseDTO;
import com.ra.model.entity.Course;
import com.ra.repository.CourseRepository;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements com.ra.service.CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<CourseResponseDTO> getCourses() {
        return courseRepository.findAll().stream().map(
                course -> CourseResponseDTO.builder()
                        .id(course.getId())
                        .courseName(course.getCourseName())
                        .description(course.getDescription())
                        .duration(course.getDuration()).build()
        ).toList();
    }

    @Override
    public CourseResponseDTO saveCourse(CourseRequestDTO courseRequestDTO) {
        Course course = Course.builder()
                .courseName(courseRequestDTO.getCourseName())
                .description(courseRequestDTO.getDescription())
                .duration(courseRequestDTO.getDuration()).build();

        Course savedCourse = courseRepository.save(course);
        return CourseResponseDTO.builder()
                .id(savedCourse.getId())
                .courseName(savedCourse.getCourseName())
                .description(savedCourse.getDescription())
                .duration(savedCourse.getDuration()).build();
    }


    @Override
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseRequestDTO) {
        Course course = Course.builder()
                .id(id)
                .courseName(courseRequestDTO.getCourseName())
                .description(courseRequestDTO.getDescription())
                .duration(courseRequestDTO.getDuration()).build();

        Course updatedCourse = courseRepository.save(course);
        return CourseResponseDTO.builder()
                .id(updatedCourse.getId())
                .courseName(updatedCourse.getCourseName())
                .description(updatedCourse.getDescription())
                .duration(updatedCourse.getDuration()).build();
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public CourseResponseDTO findCourseById(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        return CourseResponseDTO.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .description(course.getDescription())
                .duration(course.getDuration()).build();
    }

}
