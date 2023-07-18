package com.example.blogapp.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
//@Builder
public class CommentRequestDto {
    private String description;
}
