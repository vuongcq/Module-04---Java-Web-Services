package com.example.session08.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseDTO {
    private String courseName;
    private Integer duration; // Thời gian khóa học (giờ)
    private String description;
}
