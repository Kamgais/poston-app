package com.startuper.postonapp.controller;

import com.startuper.postonapp.dtos.PostDto;
import com.startuper.postonapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    public ResponseEntity<List<PostDto>> findAllPost(@RequestParam(required = false) String title) {
        if (title != null) {
            List<PostDto> posts = postService.getPostByTitle(title);
            return ResponseEntity.ok(posts);
        } else {
            return ResponseEntity.ok(postService.getAllPost());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> findPostById(@PathVariable Long id) {
        PostDto postDto = postService.getPostById(id);
        if (postDto != null) {
            return ResponseEntity.ok(postDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") Long id, @RequestBody PostDto postDto) {
        PostDto updated = postService.updatePost(id, postDto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/like/{id}/{value}")
    public ResponseEntity<String> likePost(@PathVariable("value") Long value, @PathVariable("id") Long id) {
        try {
            String message = postService.likePost(value, id);
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/unlike/{id}/{value}")
    public ResponseEntity<String> unLikePost(@PathVariable("value") Long value, @PathVariable("id") Long id) {
        try {
            String message = postService.unLikePost(value, id);
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<List<PostDto>> getPostByCategoryName(@RequestParam String categoryName) {
        List<PostDto> posts = postService.getPostByCategoryName(categoryName);
        return ResponseEntity.ok(posts);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        try {
            PostDto created = postService.addPost(postDto);
            return ResponseEntity.created(new URI("/api/posts/" + created.getId())).body(created);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable("id") Long id) {
        List<PostDto> posts = postService.getPostsByUserId(id);
        return ResponseEntity.ok(posts);
    }
}
