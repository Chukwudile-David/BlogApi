package com.example.blogapp.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Builder
@Data
public class UpdateCommentResponseDto {
    private Long id;
    private String description;
    private LocalDateTime updatedAt;
}
