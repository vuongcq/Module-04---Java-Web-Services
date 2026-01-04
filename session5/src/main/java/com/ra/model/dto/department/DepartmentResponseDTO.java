package com.ra.model.dto.department;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DepartmentResponseDTO {
    private Long id;
    private String departmentName;
    private String description;
}
