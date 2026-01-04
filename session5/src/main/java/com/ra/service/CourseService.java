package com.ra.service;

import com.ra.model.dto.course.CourseRequestDTO;
import com.ra.model.dto.course.CourseResponseDTO;
import com.ra.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    // hien thi danh sach
    List<CourseResponseDTO> getCourses();

    // them
    CourseResponseDTO saveCourse(CourseRequestDTO courseRequestDTO);

    // sua
    CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseRequestDTO);

    //xoa
    void deleteCourse(Long id);

    // tim ban ghi
    public CourseResponseDTO findCourseById(Long id);

}
