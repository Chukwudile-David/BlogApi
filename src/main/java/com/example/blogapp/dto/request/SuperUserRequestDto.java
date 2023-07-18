package com.example.blogapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuperUserRequestDto {
    @NotBlank(message = "kindly provide a username")
    private String username;
    @NotBlank(message = "kindly provide a password")
    private String password;
}
