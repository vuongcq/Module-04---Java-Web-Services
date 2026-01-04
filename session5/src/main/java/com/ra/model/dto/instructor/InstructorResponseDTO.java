package com.ra.model.dto.instructor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
@Builder
public class InstructorResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Long departmentId;
    private String departmentName;
}
