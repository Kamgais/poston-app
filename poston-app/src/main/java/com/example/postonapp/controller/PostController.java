package com.example.postonapp.controller;


import com.example.postonapp.dtos.PostDto;
import com.example.postonapp.entities.Post;
import com.example.postonapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("")
    public ResponseEntity<List<PostDto>> findAllPost() {
        return postService.getAllPost();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> findPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id , @RequestBody PostDto postDto) {
        return postService.updatePost(id,postDto);
    }

    @PostMapping("")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return postService.addPost(postDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }
}
