package com.startuper.postonapp.services;

import com.startuper.postonapp.dtos.CommentDto;
import com.startuper.postonapp.entities.Comment;
import com.startuper.postonapp.mappers.CommentMapper;
import com.startuper.postonapp.repositories.ICommentRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final ICommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentService(ICommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @CacheEvict(value = {"commentsByPost", "commentCountByPost"}, key = "#commentDto.post.id")
    public CommentDto saveComment(CommentDto commentDto) {
        Comment saved = commentRepository.save(commentMapper.toEntity(commentDto));
        return commentMapper.toDto(saved);
    }

    @Cacheable(value = "commentsByPost", key = "#postId")
    public List<CommentDto> getCommentsByPost(Long postId) {
        List<Comment> comments = commentRepository.findCommentByPostId(postId);
        return comments.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    public Long countComments(Long postId) {
        return commentRepository.countCommentByPostId(postId);
    }

    @CacheEvict(value = {"commentsByPost", "commentCountByPost"}, key = "#comment.get().post.id")
    public String deleteComment(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isEmpty()) {
            throw new IllegalArgumentException("Comment not found");
        }
        commentRepository.deleteById(commentId);
        return "Comment deleted";
    }
}
