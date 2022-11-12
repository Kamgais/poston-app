package com.example.postonapp.mappers;

import com.example.postonapp.dtos.CommentDto;
import com.example.postonapp.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentMapper implements Mapper<Comment, CommentDto>{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostMapper postMapper;
    @Override
    public Comment toEntity(CommentDto commentDto) {
        if(commentDto == null) {
            return null;
        } else {
            return Comment.builder()
                    .message(commentDto.getMessage())
                    .user(userMapper.toEntity(commentDto.getUser()))
                    .post(postMapper.toEntity(commentDto.getPost()))
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
