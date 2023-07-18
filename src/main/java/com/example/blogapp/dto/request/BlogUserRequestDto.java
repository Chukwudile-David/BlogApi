package com.example.blogapp.dto.request;

import com.example.blogapp.enums.Roles;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BlogUserRequestDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;



}
