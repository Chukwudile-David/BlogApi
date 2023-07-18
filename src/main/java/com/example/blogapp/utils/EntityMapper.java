package com.example.blogapp.utils;

import com.example.blogapp.dto.request.CommentRequestDto;
import com.example.blogapp.dto.request.PostRequestDto;
import com.example.blogapp.dto.request.SuperUserRequestDto;
import com.example.blogapp.dto.response.BlogUserResponseDto;
import com.example.blogapp.dto.response.PostResponseDto;
import com.example.blogapp.dto.response.SuperUserResponseDto;
import com.example.blogapp.entities.BlogUser;
import com.example.blogapp.entities.Comment;
import com.example.blogapp.entities.Post;
import com.example.blogapp.entities.SuperUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EntityMapper {

    public SuperUser dtoToSuperUser(SuperUserRequestDto superUserRequestDto){
        return SuperUser.builder().username(superUserRequestDto.getUsername())
                .password(superUserRequestDto.getPassword()).build();
    }

    public SuperUserResponseDto superuserToDto(SuperUser savedsuperuser){
        return SuperUserResponseDto.builder().Id(savedsuperuser.getAdmin_Id())
                .username(savedsuperuser.getUsername()).build();
    }

    public Post dtoToPost(PostRequestDto postRequestDto){
        return Post.builder().title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .category(postRequestDto.getCategory()).build();
    }

    public PostResponseDto postToDto(Post savedpost){
        return PostResponseDto.builder().id(savedpost.getPost_id())
                .title(savedpost.getTitle())
                .content(savedpost.getContent())
                .category(savedpost.getCategory())
                .createdAt(savedpost.getCreatedAt()).build();
    }

    public BlogUserResponseDto userToDto(BlogUser savedbloguser){
        return BlogUserResponseDto.builder().Id(savedbloguser.getId()).username(savedbloguser.getUsername()).build();
    }

    public Comment dtoToComment(CommentRequestDto commentRequestDto){
        return Comment.builder().description(commentRequestDto.getDescription())
                .createdAt(LocalDateTime.now()).build();
    }
}
