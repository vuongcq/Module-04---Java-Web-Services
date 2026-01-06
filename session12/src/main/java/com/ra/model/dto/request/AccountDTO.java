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
public class AccountDTO {
    @NotBlank(message = "Username can not blank")
    private String username ;
    @NotBlank(message = "Password can not blank")
    private String password ;
    @NotBlank(message = "Phone number can not blank")
    private String phoneNumber ;
    @NotBlank(message = "Full name can not blank")
    private String fullName ;
}
