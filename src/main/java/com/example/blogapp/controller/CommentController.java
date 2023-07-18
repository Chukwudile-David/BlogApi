package com.example.blogapp.controller;

import com.example.blogapp.dto.request.CommentRequestDto;
import com.example.blogapp.dto.response.UpdateCommentResponseDto;
import com.example.blogapp.services.BlogUserInterface;
import com.example.blogapp.utils.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final BlogUserInterface blogUserInterface;
    @PostMapping("/{postId}")
    ResponseEntity<?> makecomment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable("postId")Long postId, HttpServletRequest request){
        var response = blogUserInterface.makecomment(commentRequestDto,postId,request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PatchMapping("/{postId}/{commentId}")
    ResponseEntity<?> editcomment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable("postId") Long postId ,@PathVariable("commentId")Long commentId,HttpServletRequest request){
        var response = blogUserInterface.updatecomment(commentRequestDto,postId ,commentId,request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @DeleteMapping("/{postId}/{commentId}")
    ResponseEntity<?> deletecomment(@PathVariable("postId") Long postId ,@PathVariable("commentId")Long commentId,HttpServletRequest request){
        blogUserInterface.deletecomment(postId,commentId,request);
        return ResponseEntity.ok().body("Successfully deleted");
    }
    @GetMapping("/{postId}")
    ResponseEntity<GenericResponse> getAllComments(@PathVariable("postId") Long postId,HttpServletRequest request){
        GenericResponse response = blogUserInterface.getAllCommentsByBlogUser(postId,request);
        return  new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
