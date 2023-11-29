package com.startuper.postonapp.controller;


import com.startuper.postonapp.dtos.PostDto;
import com.startuper.postonapp.entities.Post;
import com.startuper.postonapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("")
    public ResponseEntity<List<PostDto>> findAllPost(@RequestParam(required = false) String title) {
        if(title != null) {
          return postService.getPostByTitle(title);
        } else {
            return ResponseEntity.ok().body(postService.getAllPost());
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> findPostById(@PathVariable Long id) {
        return ResponseEntity.ok().body(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") Long id , @RequestBody PostDto postDto) {
        return ResponseEntity.ok().body(postService.updatePost(id,postDto));
    }


    @GetMapping("/like/{id}/{value}")
    public ResponseEntity<String> likePost (@PathVariable("value") Long value, @PathVariable("id") Long id) {
        return postService.likePost(value, id);
    }

    @GetMapping("/unlike/{id}/{value}")
    public ResponseEntity<String> unLikePost (@PathVariable("value") Long value, @PathVariable("id") Long id) {
        return postService.unLikePost(value, id);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<PostDto>> getPostByCategoryName(@RequestParam String categoryName) {
        return postService.getPostByCategoryName(categoryName);
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {

        try {
            return ResponseEntity.created(new URI("/api/posts")).body(postService.addPost(postDto));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable("id") Long id) {
        return postService.getPostsByUserId(id);
    }
}
