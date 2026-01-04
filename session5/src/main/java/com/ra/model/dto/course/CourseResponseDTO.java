package com.ra.model.dto.course;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CourseResponseDTO {
    private Long id;
    private String courseName;
    private String description;
    private Integer duration;
}
