package com.example.blogapp.services;

import com.example.blogapp.dto.request.BlogUserRequestDto;
import com.example.blogapp.dto.request.CommentRequestDto;
import com.example.blogapp.dto.request.SuperUserRequestDto;
import com.example.blogapp.dto.response.BlogUserResponseDto;
import com.example.blogapp.dto.response.CommentResponseDto;
import com.example.blogapp.dto.response.SuperUserResponseDto;
import com.example.blogapp.dto.response.UpdateCommentResponseDto;
import com.example.blogapp.enums.Category;
import com.example.blogapp.utils.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface BlogUserInterface {

    GenericResponse createuser(BlogUserRequestDto blogUserRequestDto);
    GenericResponse LoginblogUser(BlogUserRequestDto blogUserRequestDto, HttpServletRequest request);
    GenericResponse updatebloguser(BlogUserRequestDto blogUserRequestDto,HttpServletRequest request);
    void deletebloguser(HttpServletRequest request);
    GenericResponse makecomment(CommentRequestDto commentRequestDto, Long PostId,HttpServletRequest request);
    GenericResponse updatecomment(CommentRequestDto commentRequestDto,Long postId,Long CommentId,HttpServletRequest request);
    void deletecomment(Long postId, Long commentId,HttpServletRequest request);
    GenericResponse getAllCommentsByBlogUser(Long postId,HttpServletRequest request);
    GenericResponse searchPostsByCategory(Category category,HttpServletRequest request);
    public GenericResponse likePost(Long PostId,HttpServletRequest request);
    GenericResponse logoutBlogUser(HttpServletRequest request);
}
