package com.example.blogapp.controller;

import com.example.blogapp.dto.request.BlogUserRequestDto;
import com.example.blogapp.dto.request.SuperUserRequestDto;
import com.example.blogapp.dto.response.SuperUserResponseDto;
import com.example.blogapp.enums.Category;
import com.example.blogapp.services.BlogUserInterface;
import com.example.blogapp.utils.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class BlogUserController {

    private final BlogUserInterface blogUserInterface;

    @PostMapping("/signup")
    ResponseEntity<?> createuser(@RequestBody @Valid BlogUserRequestDto blogUserRequestDto){
        log.info("hello I got here");
        var response = blogUserInterface.createuser(blogUserRequestDto);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/login")
    ResponseEntity<GenericResponse>Loginuser(@RequestBody @Valid BlogUserRequestDto blogUserRequestDto, HttpServletRequest request){
        var response = blogUserInterface.LoginblogUser(blogUserRequestDto,request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PatchMapping("/update")
    ResponseEntity<GenericResponse>updateuser(@RequestBody @Valid BlogUserRequestDto blogUserRequestDto,HttpServletRequest request){
        var response = blogUserInterface.updatebloguser(blogUserRequestDto,request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    ResponseEntity<?> deleteUser(HttpServletRequest request){
        blogUserInterface.deletebloguser( request);
        return ResponseEntity.ok("deleted");
    }
    @GetMapping()
    ResponseEntity<GenericResponse>findByCategory(@RequestParam("category")Category category,HttpServletRequest request){
        var response = blogUserInterface.searchPostsByCategory(category,request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/post/{postId}")
    public ResponseEntity<?> likePost(@PathVariable("postId") Long postId, HttpServletRequest request) {
        blogUserInterface.likePost(postId, request);
        return ResponseEntity.ok("Liked");
    }

    @GetMapping("/logout")
    public ResponseEntity<GenericResponse> logout(HttpServletRequest request) {
        GenericResponse response = blogUserInterface.logoutBlogUser(request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
