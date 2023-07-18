package com.example.blogapp.services.implementation;

import com.example.blogapp.dto.request.PostRequestDto;
import com.example.blogapp.dto.request.SuperUserRequestDto;
import com.example.blogapp.dto.response.PostResponseDto;
import com.example.blogapp.dto.response.SuperUserResponseDto;
import com.example.blogapp.dto.response.UpdatePostResponseDto;
import com.example.blogapp.entities.Post;
import com.example.blogapp.entities.SuperUser;
import com.example.blogapp.exceptions.UnauthorizedAccessException;
import com.example.blogapp.repository.PostRepository;
import com.example.blogapp.repository.SuperUserRepository;
import com.example.blogapp.services.AdminInterface;
import com.example.blogapp.utils.EntityMapper;
import com.example.blogapp.utils.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService implements AdminInterface {

    private final SuperUserRepository superUserRepository;
    private final PostRepository postRepository;
    private final EntityMapper entityMapper;
    @Override
    public GenericResponse createSuperUser(SuperUserRequestDto superUserRequestDto) {
        Optional<SuperUser> optionalSuperuser = superUserRepository.findByUsername(superUserRequestDto.getUsername());

        if (optionalSuperuser.isPresent()){
            throw new UnauthorizedAccessException("invalid username or password");
        }else{
            SuperUser savedsuperuser = entityMapper.dtoToSuperUser(superUserRequestDto);
            SuperUser savedUser = superUserRepository.save(savedsuperuser);
            SuperUserResponseDto superUserResponse = entityMapper.superuserToDto(savedUser);
            return new GenericResponse("SuperUser succesfully created","00",superUserResponse,HttpStatus.CREATED);
        }
    }

    @Override
    public GenericResponse LoginSuperUser(SuperUserRequestDto superUserRequestDto, HttpServletRequest request) {
        Optional<SuperUser> optionalSuperuser = superUserRepository.findByUsernameAndPassword(superUserRequestDto.getUsername(), superUserRequestDto.getPassword());
        if (optionalSuperuser.isEmpty()){
            return new GenericResponse("Invalid request","01",HttpStatus.BAD_REQUEST);
        }

        SuperUserResponseDto superuserResponse = entityMapper.superuserToDto(optionalSuperuser.get());
        HttpSession session = request.getSession();
        session.setAttribute("superuserResponse",superuserResponse);

        return new GenericResponse("succesfully logged in","00",HttpStatus.OK);


    }

    @Override
    public GenericResponse updateSuperUser(SuperUserRequestDto superUserRequestDto,HttpServletRequest request) {
//        Optional<SuperUser> optionalSuperUser = superUserRepository.findById(SuperuserId);
        HttpSession session = request.getSession();
        SuperUserResponseDto superuserResponse = (SuperUserResponseDto) session.getAttribute("superuserResponse");
        Optional<SuperUser> optionalsuperuser = superUserRepository.findById(superuserResponse.getId());

        if (optionalsuperuser.isEmpty()){
            return new GenericResponse("Invalid request","01",HttpStatus.BAD_REQUEST);
        }else{
            SuperUser superUser = optionalsuperuser.get();
            superUser.setUsername(superUserRequestDto.getUsername());
            superUser.setPassword(superUser.getPassword());

            SuperUser updatedSuperuser = superUserRepository.save(superUser);
            SuperUserResponseDto response = entityMapper.superuserToDto(updatedSuperuser);
            return new GenericResponse("succesfully updated","00",response,HttpStatus.CREATED);
        }

    }
    @Override
    public void deleteSuperuser(HttpServletRequest request){
        HttpSession session = request.getSession();
        SuperUserResponseDto superuserResponse = (SuperUserResponseDto) session.getAttribute("superuserResponse");
        Optional<SuperUser> optionalSuperUser = superUserRepository.findById(superuserResponse.getId());
        if (optionalSuperUser.isEmpty()){
            throw new UnauthorizedAccessException("This user does not exist");
        }else{
            superUserRepository.delete(optionalSuperUser.get());
        }

    }

    @Override
    public GenericResponse createPost(PostRequestDto postRequestDto,HttpServletRequest request) {
        HttpSession session = request.getSession();
        SuperUserResponseDto superuserResponse = (SuperUserResponseDto) session.getAttribute("superuserResponse");

        Optional<SuperUser> optionalSuperUser = superUserRepository.findById(superuserResponse.getId());

        if (optionalSuperUser.isEmpty()){
            return new GenericResponse("You are not authorized to make a post","01",HttpStatus.BAD_REQUEST);
        }else{
//            Post newpost = entityMapper.dtoToPost(postRequestDto);
            Post newpost = Post.builder().title(postRequestDto.getTitle())
                    .content(postRequestDto.getContent())
                    .category(postRequestDto.getCategory())
                    .createdAt(LocalDateTime.now())
                    .superUser(optionalSuperUser.get())
                    .build();
            Post savedpost = postRepository.save(newpost);

            PostResponseDto postResponseDto = entityMapper.postToDto(savedpost);
            return new GenericResponse("Succesfully Created","00",postResponseDto, HttpStatus.CREATED);

        }

    }

    @Override
    public GenericResponse updatePost(PostRequestDto postRequestDto,Long postId,HttpServletRequest request) {
        HttpSession session = request.getSession();
        SuperUserResponseDto superuserResponse = (SuperUserResponseDto) session.getAttribute("superuserResponse");

        Optional<SuperUser> optionalSuperUser = superUserRepository.findById(superuserResponse.getId());
        if (optionalSuperUser.isEmpty()){
            return new GenericResponse("You are not authorized to make a post","01",HttpStatus.BAD_REQUEST);
        }else{
            Optional<Post> optionalpost = postRepository.findById(postId);
            if (optionalpost.isEmpty()){
                return new GenericResponse("Post does not exist","01",HttpStatus.BAD_REQUEST);
            }else{
                Post post = optionalpost.get();
                post.setTitle(postRequestDto.getTitle());
                post.setContent(postRequestDto.getContent());
                post.setCategory(postRequestDto.getCategory());
                post.setUpdatedAt(LocalDateTime.now());

                Post updatedpost = postRepository.save(post);

                UpdatePostResponseDto updateresponse = UpdatePostResponseDto.builder().updatedAt(updatedpost.getUpdatedAt())
                        .id(updatedpost.getPost_id())
                        .title(updatedpost.getTitle())
                        .content(updatedpost.getContent())
                        .category(updatedpost.getCategory()).build();

                return new GenericResponse("succesfully updated","00",updateresponse,HttpStatus.CREATED);
            }
        }

    }

    @Override
    public void deletePost(Long postId,HttpServletRequest request) {
        HttpSession session = request.getSession();
        SuperUserResponseDto superuserResponse = (SuperUserResponseDto) session.getAttribute("superuserResponse");
        Optional<SuperUser> optionalSuperUser = superUserRepository.findById(superuserResponse.getId());
        if (optionalSuperUser.isEmpty()){
            throw new UnauthorizedAccessException("unauthorized access");
        }else {
            Optional<Post> optionalpost = postRepository.findById(postId);
            if (optionalpost.isEmpty()){
                throw new UnauthorizedAccessException("This post does not exist");
            }else {
                Post post = optionalpost.get();
                postRepository.delete(post);
            }
        }
    }

    @Override
    public GenericResponse getAllPostByAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SuperUserResponseDto superuserResponse = (SuperUserResponseDto) session.getAttribute("superuserResponse");
        Optional<SuperUser> optionalAppUser = superUserRepository.findById(superuserResponse.getId());
        if (optionalAppUser.isEmpty()){
            return new GenericResponse("you are not authorized","01",HttpStatus.BAD_REQUEST);
        }else{
            SuperUser superUser = optionalAppUser.get();
            List<Post> allPost = postRepository.findBySuperUser(superUser);

            List<PostResponseDto> response = allPost.stream().map(post -> PostResponseDto.builder()
                    .id(post.getPost_id())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .category(post.getCategory())
                    .createdAt(post.getCreatedAt()).build()).toList();

            return new GenericResponse("These are all the post by " + optionalAppUser.get().getUsername(),"00",response,HttpStatus.ACCEPTED);
        }
    }


}
