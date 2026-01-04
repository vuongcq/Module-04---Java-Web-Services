package com.ra.model.dto.course;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseRequestDTO {
    private String courseName;
    private String description;
    private Integer duration;
}
