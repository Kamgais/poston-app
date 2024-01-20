package com.startuper.postonapp.controller;


import com.startuper.postonapp.dtos.CommentDto;
import com.startuper.postonapp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/comments")
public class CommentController {


    @Autowired
    private CommentService commentService;


    @PostMapping("")
    public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto commentDto) {
        return commentService.saveComment(commentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable Long id) {
        return commentService.getCommentsByPost(id);
    }


    @GetMapping("/count/{id}")
    public ResponseEntity<Long> countComments(@PathVariable Long id) {
        return commentService.countComments(id);
    }
}
