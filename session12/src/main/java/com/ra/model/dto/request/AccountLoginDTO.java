package com.ra.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountLoginDTO {
    @NotBlank(message = "Username can not blank")
    private String username;
    @NotBlank(message = "Password can not blank")
    private String password;
}
