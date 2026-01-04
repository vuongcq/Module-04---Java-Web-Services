package com.ra.model.dto.instructor;

import com.ra.model.entity.Department;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class InstructorRequestDTO {
    private String name;
    private String email;
    private Long departmentId;
}
