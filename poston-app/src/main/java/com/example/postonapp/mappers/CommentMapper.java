package com.example.postonapp.mappers;

import com.example.postonapp.dtos.CommentDto;
import com.example.postonapp.entities.Comment;
import com.example.postonapp.entities.Post;
import com.example.postonapp.entities.User;
import com.example.postonapp.repositories.IPostRepository;
import com.example.postonapp.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CommentMapper implements Mapper<Comment, CommentDto> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserRepository userRepository;


    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private PostMapper postMapper;
    @Override
    public Comment toEntity(CommentDto commentDto) {
        if(commentDto == null) {
            return null;
        } else {
            Optional<User> user = userRepository.findById(commentDto.getUser().getId());
            Optional<Post> post = postRepository.findById(commentDto.getPost().getId());

            return Comment.builder()
                    .message(commentDto.getMessage())
                    .user(user.get())
                    .post(post.get())
                    .build();
        }

    }

    @Override
    public CommentDto toDto(Comment comment) {
        if(comment == null) {
            return null;
        } else {
            return CommentDto.builder()
                    .id(comment.getId())
                    .message(comment.getMessage())
                    .user(userMapper.toDto(comment.getUser()))
                    .build();
        }

    }
}
