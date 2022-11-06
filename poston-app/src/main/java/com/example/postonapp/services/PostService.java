package com.example.postonapp.services;


import com.example.postonapp.dtos.PostDto;
import com.example.postonapp.entities.Category;
import com.example.postonapp.entities.Post;

import com.example.postonapp.mappers.CategoryMapper;
import com.example.postonapp.mappers.PostMapper;
import com.example.postonapp.repositories.ICategoryRepository;
import com.example.postonapp.repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {



    @Autowired
    PostMapper postMapper;
    @Autowired
    IPostRepository postRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    ICategoryRepository categoryRepository;

    // get all posts
    public ResponseEntity<List<PostDto>> getAllPost() {
     List<Post> posts = (List<Post>) postRepository.findAll();
     List<PostDto> postDtos = posts.stream().map(e-> postMapper.toDto(e)).collect(Collectors.toList());
     return ResponseEntity.ok().body(postDtos);
    }

    // get post by id
    public ResponseEntity<PostDto> getPostById(Long id) {

        Optional<Post> post = postRepository.findById(id);

        if(post.isPresent()) {
            return ResponseEntity.ok().body(postMapper.toDto(post.get()));
        }

        else {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<PostDto> updatePost(Long id,PostDto updatedPost) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            Post newPost = post.get();
            newPost.setTitle(updatedPost.getTitle());
            newPost.setContent(updatedPost.getContent());
            newPost.setPostImage(updatedPost.getPostImage());
            newPost.setDateCreated(updatedPost.getDateCreated());
            newPost.setLikeCount(updatedPost.getLikeCount());


            return ResponseEntity.ok().body(postMapper.toDto(postRepository.save(newPost)));
        }

        return ResponseEntity.badRequest().build();

    }


     // add a new post
    public ResponseEntity<PostDto> addPost(PostDto post) {
        Post newPost = postRepository.save(postMapper.toEntity(post));
        return ResponseEntity.ok().body(postMapper.toDto(newPost));
    }

    public ResponseEntity<String> deletePost(Long id) {
        String message;
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()) {
            postRepository.deleteById(id);
            message = " Post deleted sucessful";
            return ResponseEntity.ok().body(message);
        }
        message = "no post found with this id";
        return ResponseEntity.ok().body(message);
    }

    public List<Post> getPostByCategoryName(String categoryName) {
        Category category =  categoryRepository.findCategoryByCategoryName(categoryName);
        return new ArrayList<Post>();


    }

}
