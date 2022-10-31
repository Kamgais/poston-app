package com.example.postonapp.services;


import com.example.postonapp.entities.Post;

import com.example.postonapp.repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    IPostRepository postRepository;

    // get all posts
    public List<Post> getAllPost() {
     List<Post> posts = (List<Post>) postRepository.findAll();

     return posts;
    }

    // get post by id
    public Post getPostById(Long id) {

        Optional<Post> post = postRepository.findById(id);

        if(post.isPresent()) {
            return post.get();
        }

        else {
            return new Post();
        }
    }

    public Post updatePost(Post updatedPost,Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            Post newPost = post.get();
            newPost.setTitle(updatedPost.getTitle());
            newPost.setContent(updatedPost.getContent());
            newPost.setPostImage(updatedPost.getPostImage());
            newPost.setDateCreated(updatedPost.getDateCreated());
            newPost.setLikeCount(updatedPost.getLikeCount());

            return postRepository.save(newPost);
        }

        return null;

    }



    public Post addPost(Post post) {
        Post newPost = postRepository.save(post);

        return newPost;
    }

    public String deletePost(Long id) {
        String message;
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()) {
            postRepository.deleteById(id);
            message = " Post deleted sucessful";
            return message;
        }
        message = "no post found with this id";
        return message;
    }

}
