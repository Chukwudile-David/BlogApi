package com.example.blogapp.controller;

import com.example.blogapp.dto.request.PostRequestDto;
import com.example.blogapp.dto.request.SuperUserRequestDto;
import com.example.blogapp.services.AdminInterface;
import com.example.blogapp.utils.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SuperuserController {

    private final AdminInterface adminInterface;


    @PostMapping("/signup")
    ResponseEntity<GenericResponse > createSuperuser(@RequestBody @Valid SuperUserRequestDto superUserRequestDto){
        log.info("hello I got here");
        GenericResponse response = adminInterface.createSuperUser(superUserRequestDto);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<GenericResponse>LoginSuperuser(@RequestBody @Valid SuperUserRequestDto superUserRequestDto, HttpServletRequest request){
        var response = adminInterface.LoginSuperUser(superUserRequestDto,request);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
    @PatchMapping()
    ResponseEntity<?>updateSuperuser(@RequestBody @Valid SuperUserRequestDto superUserRequestDto,HttpServletRequest request){
        var response = adminInterface.updateSuperUser(superUserRequestDto,request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping()
    ResponseEntity<?> deleteUser(HttpServletRequest request){
         adminInterface.deleteSuperuser(request);
         return ResponseEntity.ok("deleted");
    }
   @PostMapping("/post")
    public ResponseEntity<GenericResponse> createpost(@RequestBody PostRequestDto postRequestDto,HttpServletRequest httpServletRequest){
       GenericResponse genericResponse = adminInterface.createPost(postRequestDto, httpServletRequest);
       return new ResponseEntity<>(genericResponse,HttpStatus.CREATED);
    }

    @PatchMapping("/{postId}")
    ResponseEntity<GenericResponse>updatepost(@RequestBody @Valid PostRequestDto postRequestDto,@PathVariable("postId")Long postId,HttpServletRequest request){
        var response = adminInterface.updatePost(postRequestDto,postId,request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    ResponseEntity<?> deletepost(@PathVariable("postId")Long PostId,HttpServletRequest request){
        adminInterface.deletePost(PostId,request);
        return ResponseEntity.ok("deleted");
    }
    @GetMapping()
    ResponseEntity<GenericResponse> getAllPostbyAdmin(HttpServletRequest request){
        GenericResponse response = adminInterface.getAllPostByAdmin(request);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
}
