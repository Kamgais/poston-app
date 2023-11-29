package com.startuper.postonapp.services;


import com.startuper.postonapp.dtos.PostDto;
import com.startuper.postonapp.entities.*;

import com.startuper.postonapp.mappers.CategoryMapper;
import com.startuper.postonapp.mappers.PostMapper;
import com.startuper.postonapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

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


    @Autowired
    ITagRepository tagRepository;

    // get all posts
    public List<PostDto> getAllPost() {



        List<Post> posts = (List<Post>) postRepository.findAll();
        List<PostDto> postDtos = posts.stream().map(e -> postMapper.toDto(e)).collect(Collectors.toList());
        return postDtos;
    }

    // get post by id
    public PostDto getPostById(Long id) {

        Optional<Post> post = postRepository.findById(id);

        if (post.isPresent()) {
            return postMapper.toDto(post.get());
        } else {
            // TODO : errors handling
            return null;
        }
    }

    public PostDto updatePost(Long id, PostDto updatedPost) {
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


            return postMapper.toDto(postRepository.save(newPost));
        }
        // TODO : errors handling
        return null;

    }


    // add a new post

    public PostDto addPost(PostDto post) {
        Post newPost = postRepository.save(postMapper.toEntity(post));
        return postMapper.toDto(newPost);
    }

    public void deletePost(Long id) {
        String message;
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            List<Notification> notifs = notificationRepository.findAllByPostId(post.get().getId());
            List<Comment> comments = commentRepository.findCommentByPostId(post.get().getId());
            List<Tag> tags = tagRepository.findTagsByPostId(post.get().getId());
            tagRepository.deleteAll(tags);
            notificationRepository.deleteAll(notifs);
            commentRepository.deleteAll(comments);
            postRepository.deleteById(id);
            imageRepository.deleteById(post.get().getImage().getId());

        }

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

    public ResponseEntity<List<PostDto>> getPostsByUserId (Long id) {
        List<Post> posts = postRepository.findPostsByUserId(id);

        return ResponseEntity.ok().body(posts.stream().map(e-> postMapper.toDto(e)).collect(Collectors.toList()));
    }

}
