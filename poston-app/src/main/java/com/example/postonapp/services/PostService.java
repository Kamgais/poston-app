package com.example.postonapp.services;


import com.example.postonapp.dtos.PostDto;
import com.example.postonapp.entities.Category;
import com.example.postonapp.entities.Notification;
import com.example.postonapp.entities.Post;

import com.example.postonapp.mappers.CategoryMapper;
import com.example.postonapp.mappers.PostMapper;
import com.example.postonapp.repositories.*;
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

    @Autowired
    INotificationRepository notificationRepository;

    @Autowired
    ICommentRepository commentRepository;

    @Autowired
    ImageService imageService;

    @Autowired
    IImageRepository imageRepository;

    // get all posts
    public ResponseEntity<List<PostDto>> getAllPost() {



        List<Post> posts = (List<Post>) postRepository.findAll();
        List<PostDto> postDtos = posts.stream().map(e -> postMapper.toDto(e)).collect(Collectors.toList());
        return ResponseEntity.ok().body(postDtos);
    }

    // get post by id
    public ResponseEntity<PostDto> getPostById(Long id) {

        Optional<Post> post = postRepository.findById(id);

        if (post.isPresent()) {
            return ResponseEntity.ok().body(postMapper.toDto(post.get()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<PostDto> updatePost(Long id, PostDto updatedPost) {
        Optional<Post> post = postRepository.findById(id);
        Post toPost = postMapper.toEntity(updatedPost);
        if (post.isPresent()) {
            Post newPost = post.get();
            newPost.setTitle(toPost.getTitle());
            newPost.setContent(toPost.getContent());
            newPost.setDateCreated(toPost.getDateCreated());
            newPost.setLikeCount(toPost.getLikeCount());
            newPost.setUnlikeCount(toPost.getUnlikeCount());
            newPost.setImage(toPost.getImage());
            newPost.setCategories(toPost.getCategories());


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
        if (post.isPresent()) {
            List<Notification> notifs = notificationRepository.findAllByPostId(post.get().getId());
            notificationRepository.deleteAll(notifs);
            imageRepository.deleteById(post.get().getImage().getId());
            postRepository.deleteById(id);
            message = " Post deleted sucessful";
            return ResponseEntity.ok().body(message);
        }
        message = "no post found with this id";
        return ResponseEntity.ok().body(message);
    }


    public ResponseEntity<String> likePost(Long likeCounter, Long id) {
        String message;
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            post.get().setLikeCount(post.get().getLikeCount() + likeCounter);
            postRepository.save(post.get());
            return ResponseEntity.ok().body("post gelikt");
        } else {
            return ResponseEntity.badRequest().build();
        }


    }


    public ResponseEntity<String> unLikePost(Long unlikeCounter, Long id) {
        String message;
        Optional<Post> post = postRepository.findById(id);

        if (post.isPresent()) {
            post.get().setUnlikeCount(post.get().getUnlikeCount() + unlikeCounter);
            postRepository.save(post.get());
            return ResponseEntity.ok().body("post ungelikt");

        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<List<PostDto>> getPostByCategoryName(String categoryName) {
        Category category = categoryRepository.findCategoryByCategoryName(categoryName);
        List<Post> posts = (List<Post>) postRepository.findAll();
        List<Post> foundPosts = posts.stream().filter(e -> e.getCategories().contains(category)).collect(Collectors.toList());
        List<PostDto> foundPostDtos = foundPosts.stream().map(e -> postMapper.toDto(e)).collect(Collectors.toList());
        return ResponseEntity.ok().body(foundPostDtos);


    }


    public ResponseEntity<List<PostDto>> getPostByTitle(String postTitle) {
        List<Post> posts = postRepository.findPostByTitle(postTitle);
        return ResponseEntity.ok().body(posts.stream().limit(8).map(e -> postMapper.toDto(e)).collect(Collectors.toList()));
    }

}
