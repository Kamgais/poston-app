package com.startuper.postonapp.services;


import com.startuper.postonapp.dtos.CommentDto;
import com.startuper.postonapp.entities.Comment;
import com.startuper.postonapp.mappers.CommentMapper;
import com.startuper.postonapp.repositories.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {



    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;



    public ResponseEntity<CommentDto> saveComment(CommentDto commentDto) {
        Comment comment = commentRepository.save(commentMapper.toEntity(commentDto));
        return ResponseEntity.ok().body(commentMapper.toDto(comment));

    }


    public ResponseEntity<List<CommentDto>> getCommentsByPost(Long id) {
        List<Comment> comments =  commentRepository.findCommentByPostId(id);
        return ResponseEntity.ok().body(comments.stream().map(e->commentMapper.toDto(e)).collect(Collectors.toList()));
    }


    public ResponseEntity<Long> countComments(Long id) {
        Long count = commentRepository.countCommentByPostId(id);
        return ResponseEntity.ok().body(count);
    }


    public ResponseEntity<String> deleteComment(Long id) {
       Optional<Comment> comment =  commentRepository.findById(id);

       if(!comment.isPresent()) {
           return ResponseEntity.badRequest().build();
       } else {
           commentRepository.deleteById(id);
           return ResponseEntity.ok().body("comment deleted");
       }
    }



}
