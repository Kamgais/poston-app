package com.startuper.postonapp.services;

import com.startuper.postonapp.dtos.PostDto;
import com.startuper.postonapp.entities.*;
import com.startuper.postonapp.mappers.CategoryMapper;
import com.startuper.postonapp.mappers.PostMapper;
import com.startuper.postonapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private IPostRepository postRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private INotificationRepository notificationRepository;

    @Autowired
    private ICommentRepository commentRepository;

    @Autowired
    private ITagRepository tagRepository;

    @Cacheable(value = "postsAll", key = "'all'")
    public List<PostDto> getAllPost() {
        List<Post> posts = (List<Post>) postRepository.findAll();
        if (posts.isEmpty()) {
            return new ArrayList<>();
        }
        return posts.stream().map(postMapper::toDto).collect(Collectors.toList());
    }

    @Cacheable(value = "postById", key = "#id")
    public PostDto getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.map(postMapper::toDto).orElse(null);
    }

    @CacheEvict(value = {"postsAll", "postById", "postByCategory", "postByTitle", "postsByUser"}, allEntries = true)
    public PostDto addPost(PostDto post) {
        Post newPost = postRepository.save(postMapper.toEntity(post));
        return postMapper.toDto(newPost);
    }

    @CacheEvict(value = {"postsAll", "postById", "postByCategory", "postByTitle", "postsByUser"}, allEntries = true)
    public List<PostDto> addPosts(List<PostDto> dtos) {
        List<Post> posts = dtos.stream().map(postMapper::toEntity).collect(Collectors.toList());
        postRepository.saveAll(posts);
        return posts.stream().map(postMapper::toDto).collect(Collectors.toList());
    }

    @CacheEvict(value = {"postsAll", "postById", "postByCategory", "postByTitle", "postsByUser"}, allEntries = true)
    public PostDto updatePost(Long id, PostDto updatedPost) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            Post toPost = postMapper.toEntity(updatedPost);
            Post newPost = post.get();
            newPost.setTitle(toPost.getTitle());
            newPost.setContent(toPost.getContent());
            newPost.setDateCreated(toPost.getDateCreated());
            newPost.setLikeCount(toPost.getLikeCount());
            newPost.setUnlikeCount(toPost.getUnlikeCount());
            newPost.setPostImage(toPost.getPostImage());
            newPost.setCategories(toPost.getCategories());
            return postMapper.toDto(postRepository.save(newPost));
        }
        return null;
    }

    @CacheEvict(value = {"postsAll", "postById", "postByCategory", "postByTitle", "postsByUser"}, allEntries = true)
    public void deletePost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            List<Notification> notifs = notificationRepository.findAllByPostId(post.get().getId());
            List<Comment> comments = commentRepository.findCommentByPostId(post.get().getId());
            List<Tag> tags = tagRepository.findTagsByPostId(post.get().getId());
            tagRepository.deleteAll(tags);
            notificationRepository.deleteAll(notifs);
            commentRepository.deleteAll(comments);
            postRepository.deleteById(id);
        }
    }

    @CacheEvict(value = "postById", key = "#id")
    public String likePost(Long likeCounter, Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            post.get().setLikeCount(post.get().getLikeCount() + likeCounter);
            postRepository.save(post.get());
            return "post liked";
        } else {
            throw new IllegalArgumentException("Post not found");
        }
    }

    @CacheEvict(value = "postById", key = "#id")
    public String unLikePost(Long unlikeCounter, Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            post.get().setUnlikeCount(post.get().getUnlikeCount() + unlikeCounter);
            postRepository.save(post.get());
            return "post unliked";
        } else {
            throw new IllegalArgumentException("Post not found");
        }
    }

    @Cacheable(value = "postByCategory", key = "#categoryName")
    public List<PostDto> getPostByCategoryName(String categoryName) {
        Category category = categoryRepository.findCategoryByCategoryName(categoryName);
        List<Post> posts = (List<Post>) postRepository.findAll();
        List<Post> foundPosts = posts.stream()
                .filter(p -> p.getCategories().contains(category))
                .collect(Collectors.toList());
        return foundPosts.stream().map(postMapper::toDto).collect(Collectors.toList());
    }

    @Cacheable(value = "postByTitle", key = "#postTitle")
    public List<PostDto> getPostByTitle(String postTitle) {
        List<Post> posts = postRepository.findPostByTitle(postTitle);
        return posts.stream().limit(8).map(postMapper::toDto).collect(Collectors.toList());
    }

    @Cacheable(value = "postsByUser", key = "#id")
    public List<PostDto> getPostsByUserId(Long id) {
        List<Post> posts = postRepository.findPostsByUserId(id);
        return posts.stream().map(postMapper::toDto).collect(Collectors.toList());
    }
}
