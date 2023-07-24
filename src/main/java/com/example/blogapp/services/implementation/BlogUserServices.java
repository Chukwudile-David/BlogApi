package com.example.blogapp.services.implementation;

import com.example.blogapp.dto.request.BlogUserRequestDto;
import com.example.blogapp.dto.request.CommentRequestDto;
import com.example.blogapp.dto.response.*;
import com.example.blogapp.entities.BlogUser;
import com.example.blogapp.entities.Comment;
import com.example.blogapp.entities.Likes;
import com.example.blogapp.entities.Post;
import com.example.blogapp.enums.Category;
import com.example.blogapp.exceptions.UnauthorizedAccessException;
import com.example.blogapp.repository.BlogUserRepository;
import com.example.blogapp.repository.CommentRepository;
import com.example.blogapp.repository.LikeRepository;
import com.example.blogapp.repository.PostRepository;
import com.example.blogapp.services.BlogUserInterface;
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

@RequiredArgsConstructor
@Service
public class BlogUserServices implements BlogUserInterface {

    private final BlogUserRepository blogUserRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private  final LikeRepository likeRepository;
    private final EntityMapper entityMapper;


    @Override
    public GenericResponse createuser(BlogUserRequestDto blogUserRequestDto) {
        Optional<?> optionalSuperuser = blogUserRepository.findByUsername(blogUserRequestDto.getUsername());
        if (optionalSuperuser.isPresent()) {
            return new GenericResponse("This username is taken","01",HttpStatus.BAD_REQUEST);
        } else {
            BlogUser build = BlogUser.builder().username(blogUserRequestDto.getUsername())
                    .password(blogUserRequestDto.getPassword()).build();

            BlogUser saveduser = blogUserRepository.save(build);

            BlogUserResponseDto userresponse = BlogUserResponseDto.builder().Id(saveduser.getId()).username(saveduser.getUsername())
                    .build();
            return new GenericResponse("Successfully created","00",userresponse,HttpStatus.CREATED);
        }
    }

    @Override
    public GenericResponse LoginblogUser(BlogUserRequestDto blogUserRequestDto, HttpServletRequest request) {
        Optional<BlogUser> optionalbloguser = blogUserRepository.findByUsernameAndPassword(blogUserRequestDto.getUsername(), blogUserRequestDto.getPassword());
        if (optionalbloguser.isEmpty()) {
            return new GenericResponse("invalid username or password","01",HttpStatus.BAD_REQUEST);
        }
        BlogUserResponseDto blogUserResponse = entityMapper.userToDto(optionalbloguser.get());
        HttpSession session = request.getSession();
        session.setAttribute("bloguser", blogUserResponse);

        BlogUserResponseDto blogresponse = BlogUserResponseDto.builder().username(optionalbloguser.get().getUsername())
                .Id(optionalbloguser.get().getId()).build();

        return new GenericResponse("successfully logged in","00",blogresponse,HttpStatus.OK);
    }

    @Override
    public GenericResponse updatebloguser(BlogUserRequestDto blogUserRequestDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        BlogUserResponseDto bloguser=(BlogUserResponseDto) session.getAttribute("bloguser");
        Optional<BlogUser> optionalblogUser = blogUserRepository.findById(bloguser.getId());
        if (optionalblogUser.isEmpty()) {
            return new GenericResponse("Invalid request","00",HttpStatus.BAD_REQUEST);
        } else {
            BlogUser blogUser = optionalblogUser.get();
            blogUser.setUsername(blogUserRequestDto.getUsername());
            blogUser.setPassword(blogUserRequestDto.getPassword());

            BlogUser updatedbloguser = blogUserRepository.save(blogUser);

            BlogUserResponseDto response  = BlogUserResponseDto.builder().Id(updatedbloguser.getId())
                    .username(updatedbloguser.getUsername()).build();

            return new GenericResponse("Successfully updated","00",response,HttpStatus.OK);
        }
    }

    @Override
    public void deletebloguser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        BlogUserResponseDto response = (BlogUserResponseDto) session.getAttribute("bloguser");
        Optional<BlogUser> optionalblogUser = blogUserRepository.findById(response.getId());
        if (optionalblogUser.isEmpty()) {
            throw new UnauthorizedAccessException("Invalid request");
        } else {
            blogUserRepository.delete(optionalblogUser.get());
        }
    }

    @Override
    public GenericResponse makecomment(CommentRequestDto commentRequestDto, Long PostId,HttpServletRequest request) {
        HttpSession session = request.getSession();
        BlogUserResponseDto response = (BlogUserResponseDto)session.getAttribute("bloguser");
        Optional<BlogUser> optionalblogUser = blogUserRepository.findById(response.getId());
        if (optionalblogUser.isEmpty()) {
            return new GenericResponse("Invalid request","00",HttpStatus.BAD_REQUEST);
        } else {
            Optional<Post> optionalpost = postRepository.findById(PostId);
            if (optionalpost.isEmpty()) {
                return new GenericResponse("Invalid request","00",HttpStatus.BAD_REQUEST);
            } else {
                Comment comment = Comment.builder().description(commentRequestDto.getDescription())
                        .blogUser(optionalblogUser.get())
                        .post(optionalpost.get())
                        .createdAt(LocalDateTime.now()).build();

                Comment savedcomment = commentRepository.save(comment);

                CommentResponseDto commentResponse = CommentResponseDto.builder()
                        .id(savedcomment.getComment_Id())
                        .description(savedcomment.getDescription())
                        .createdAt(savedcomment.getCreatedAt()).build();

                return new GenericResponse("comment succesfully created","00",commentResponse,HttpStatus.CREATED);
            }
        }

    }


    @Override
    public GenericResponse updatecomment(CommentRequestDto commentRequestDto, Long postId, Long commentId,HttpServletRequest request) {
        HttpSession session = request.getSession();
        BlogUserResponseDto response = (BlogUserResponseDto)session.getAttribute("bloguser");
        Optional<BlogUser> optionalBlogUser = blogUserRepository.findById(response.getId());
        if (optionalBlogUser.isEmpty()) {
            return new GenericResponse("Invalid request","01",HttpStatus.BAD_REQUEST);
        }

        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isEmpty()) {
            return new GenericResponse("Invalid request","01",HttpStatus.BAD_REQUEST);
        }

        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isEmpty()) {
            return new GenericResponse("Invalid request","01",HttpStatus.BAD_REQUEST);
        }

        Comment comment = optionalComment.get();
        if (!comment.getBlogUser().getId().equals(optionalBlogUser.get().getId())) {
            return new GenericResponse("you cannot edit this comment","01",HttpStatus.BAD_REQUEST);
        }

        if (!comment.getPost().getPost_id().equals(optionalPost.get().getPost_id())) {
            return new GenericResponse("This comment does not belong to the specified post","01",HttpStatus.BAD_REQUEST);
        }

        comment.setDescription(commentRequestDto.getDescription());
        comment.setUpdatedAt(LocalDateTime.now());
        Comment savedComment = commentRepository.save(comment);

        UpdateCommentResponseDto response1 = UpdateCommentResponseDto.builder()
                .id(savedComment.getComment_Id())
                .description(savedComment.getDescription())
                .updatedAt(savedComment.getUpdatedAt())
                .build();
        return new GenericResponse("Successsfully updated","00",response1,HttpStatus.OK);
    }

    @Override
    public void deletecomment(Long postId, Long commentId,HttpServletRequest request) {
        HttpSession session = request.getSession();
        BlogUserResponseDto response = (BlogUserResponseDto)session.getAttribute("bloguser");
        Optional<BlogUser> optionalBlogUser = blogUserRepository.findById(response.getId());
        if (optionalBlogUser.isEmpty()) {
            throw new UnauthorizedAccessException("Unauthorized user");
        }

        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isEmpty()) {
            throw new UnauthorizedAccessException("Invalid post");
        }

        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isEmpty()) {
            throw new UnauthorizedAccessException("Invalid comment");
        }

        Comment comment = optionalComment.get();
        if (!comment.getBlogUser().getId().equals(optionalBlogUser.get().getId())) {
            throw new UnauthorizedAccessException("You cannot delete this comment");
        }

        if (!comment.getPost().getPost_id().equals(optionalPost.get().getPost_id())) {
            throw new UnauthorizedAccessException("This comment does not belong to the specified post");
        }

        commentRepository.delete(comment);
    }

    @Override
    public GenericResponse getAllCommentsByBlogUser(Long postId,HttpServletRequest request) {
        HttpSession session = request.getSession();
        BlogUserResponseDto response = (BlogUserResponseDto)session.getAttribute("bloguser");
        Optional<BlogUser> optionalBlogUser = blogUserRepository.findById(response.getId());
        if (optionalBlogUser.isEmpty()) {
            return new GenericResponse("Invalid request","01",HttpStatus.BAD_REQUEST);
        }

        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isEmpty()) {
            return new GenericResponse("Invalid request","01",HttpStatus.BAD_REQUEST);
        }

        BlogUser blogUser = optionalBlogUser.get();
        Post post = optionalPost.get();
        List<Comment> userComments = commentRepository.findByBlogUserAndPost(blogUser, post);

        List<CommentResponseDto> response1 = userComments.stream()
                .map(comment -> CommentResponseDto.builder()
                        .id(comment.getComment_Id())
                        .description(comment.getDescription())
                        .createdAt(comment.getCreatedAt())
                        .build())
                .toList();
        return new GenericResponse("All comments on Post " + optionalPost.get().getPost_id() + " " +"by " + optionalBlogUser.get().getUsername(),"00",response1,HttpStatus.ACCEPTED);
    }

    public GenericResponse searchPostsByCategory(Category category,HttpServletRequest request) {
        HttpSession session = request.getSession();
        BlogUserResponseDto response = (BlogUserResponseDto)session.getAttribute("bloguser");
        Optional<BlogUser> optionalBlogUser = blogUserRepository.findById(response.getId());
        if (optionalBlogUser.isEmpty()) {
            return new GenericResponse("Invalid request","01",HttpStatus.BAD_REQUEST);
        }

        List<Post> Postlist = postRepository.findAllByCategory(category);

        List<PostResponseDto> postresponse = Postlist.stream()
                .map(post -> PostResponseDto.builder()
                        .id(post.getPost_id())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .category(post.getCategory())
                        .createdAt(post.getCreatedAt())
                        .build())
                .toList();

        return new GenericResponse("All posts in category " + category, "00",postresponse, HttpStatus.ACCEPTED);

    }

    @Override
    public GenericResponse likePost(Long PostId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        BlogUserResponseDto response = (BlogUserResponseDto)session.getAttribute("bloguser");
        Optional<BlogUser> optionalBlogUser = blogUserRepository.findById(response.getId());
        if (optionalBlogUser.isEmpty()) {
            throw new UnauthorizedAccessException("Unauthorized user");
        }
        Optional<Post> optionalPost = postRepository.findById(PostId);
        if (optionalPost.isEmpty()) {
           throw new UnauthorizedAccessException("This post does not exist");
        }
        BlogUser blogUser = optionalBlogUser.get();
        Post post = optionalPost.get();

        if (likeRepository.existsByPostAndUser(post, blogUser)) {
            throw new UnauthorizedAccessException("You have already liked this post");
        }

        Likes like = new Likes();
        like.setPost(post);
        like.setUser(blogUser);
        likeRepository.save(like);
        return new GenericResponse("succesfully liked","00",like);
    }

    @Override
    public GenericResponse logoutBlogUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Retrieve the existing session, if it exists

        if (session != null) {
            session.invalidate(); // Invalidate the session to log the user out
            return new GenericResponse("Successfully logged out", "00", HttpStatus.OK);
        } else {
            return new GenericResponse("No active session found", "01", HttpStatus.BAD_REQUEST);
        }
    }


}

