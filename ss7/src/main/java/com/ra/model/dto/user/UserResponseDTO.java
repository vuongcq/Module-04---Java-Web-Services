package com.ra.model.dto.user;

import com.ra.model.entity.Role;
import jakarta.persistence.Column;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDTO {
    private Long id;
    @Column(name = "username")
    private String username;
    private String typeToken;
    private String accessToken;
    private Set<Role> roles;
}
