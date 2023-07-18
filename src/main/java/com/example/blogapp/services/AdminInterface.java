package com.example.blogapp.services;

import com.example.blogapp.dto.request.PostRequestDto;
import com.example.blogapp.dto.request.SuperUserRequestDto;
import com.example.blogapp.utils.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AdminInterface {
    GenericResponse createSuperUser(SuperUserRequestDto superUserRequestDto);
    GenericResponse LoginSuperUser(SuperUserRequestDto superUserRequestDto, HttpServletRequest request);
    GenericResponse updateSuperUser(SuperUserRequestDto superUserRequestDto,HttpServletRequest request);
    void deleteSuperuser(HttpServletRequest request);
    GenericResponse createPost(PostRequestDto postRequestDto,HttpServletRequest request);
    GenericResponse updatePost(PostRequestDto postRequestDto,Long postId,HttpServletRequest request);
    void deletePost(Long postId,HttpServletRequest request);
    GenericResponse getAllPostByAdmin(HttpServletRequest request);


}
