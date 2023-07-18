package com.example.blogapp.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Builder
@Data
public class SuperUserResponseDto {
   private Long Id;
   private String username;
}
