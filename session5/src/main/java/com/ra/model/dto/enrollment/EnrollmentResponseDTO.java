package com.ra.model.dto.enrollment;

import com.ra.model.entity.Course;
import com.ra.model.entity.Student;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnrollmentResponseDTO {
    private Long id;
    private Long studentID;
    private Long courseId;
    private LocalDate enrollmentDate;
}
