package com.ra.model.dto.student;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StudentResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
}
