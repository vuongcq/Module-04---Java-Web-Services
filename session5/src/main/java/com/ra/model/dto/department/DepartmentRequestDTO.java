package com.ra.model.dto.department;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class DepartmentRequestDTO {
    private String departmentName;
    private String description;
}
