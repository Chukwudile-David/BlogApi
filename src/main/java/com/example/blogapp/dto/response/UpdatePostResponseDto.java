package com.example.blogapp.dto.response;

import com.example.blogapp.enums.Category;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;
@Builder
@Data
public class UpdatePostResponseDto {

    private Long id;
    private String title;
    private String content;
    private Category category;
    private LocalDateTime updatedAt;
}
