package com.example.postonapp.controller;


import com.example.postonapp.entities.Post;
import com.example.postonapp.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public List<Post> findAllPost() {
        return postService.getAllPost();
    }
}
