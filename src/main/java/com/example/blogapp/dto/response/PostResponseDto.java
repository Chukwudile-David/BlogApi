package com.example.blogapp.dto.response;

import com.example.blogapp.enums.Category;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private Category category;
    private LocalDateTime createdAt;
}
