package com.example.blogapp.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BlogUserResponseDto {
    private Long Id;
    private String username;
//    private String message;
}
