package com.startuper.postonapp.controller;

import com.startuper.postonapp.dtos.CommentDto;
import com.startuper.postonapp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("")
    public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto commentDto) {
        CommentDto saved = commentService.saveComment(commentDto);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        try {
            String message = commentService.deleteComment(id);
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Comment not found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable("id") Long id) {
        List<CommentDto> comments = commentService.getCommentsByPost(id);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/count/{id}")
    public ResponseEntity<Long> countComments(@PathVariable("id") Long id) {
        Long count = commentService.countComments(id);
        return ResponseEntity.ok(count);
    }
}
