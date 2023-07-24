package com.example.blogapp.controller;

import com.example.blogapp.services.BlogUserInterface;
import com.example.blogapp.utils.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class LikeController {

    private final BlogUserInterface blogUserInterface;

    @PostMapping("/{postId}/like")
    public ResponseEntity<GenericResponse> likePost(@PathVariable Long postId, HttpServletRequest request) {
        GenericResponse response = blogUserInterface.likePost(postId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
