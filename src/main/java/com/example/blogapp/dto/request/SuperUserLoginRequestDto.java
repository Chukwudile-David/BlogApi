package com.example.blogapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuperUserLoginRequestDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
