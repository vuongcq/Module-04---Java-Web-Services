package com.ra.model.dto.user;

import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class LoginRequestDTO {
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}
