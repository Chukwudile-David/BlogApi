package com.example.blogapp.dto.request;

import com.example.blogapp.enums.Roles;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BlogUserRequestDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;



}
