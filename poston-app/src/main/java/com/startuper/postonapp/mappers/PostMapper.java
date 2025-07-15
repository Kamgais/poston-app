package com.startuper.postonapp.mappers;

import com.startuper.postonapp.dtos.PostDto;
import com.startuper.postonapp.entities.*;
import com.startuper.postonapp.repositories.ICategoryRepository;
import com.startuper.postonapp.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@RequiredArgsConstructor
@Service
public class PostMapper implements  Mapper<Post, PostDto>{



   private final IUserRepository userRepository;
    private final ICategoryRepository categoryRepository;



    @Autowired
    CategoryMapper categoryMapper ;

    @Autowired
    UserMapper userMapper ;









    @Override
    public Post toEntity(PostDto postDto) {
        if(postDto == null) {
            return null;
        }

        User user = userRepository.findById(postDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + postDto.getUserId()));

        // Map category names to entities, filtering out nulls or throw if any missing
        List<Category> categories = postDto.getCategories().stream()
                .map(name -> categoryRepository.findCategoryByCategoryName(name))
                .filter(cat -> cat != null) // Option 1: filter nulls
                .collect(Collectors.toList());

        if(categories.size() != postDto.getCategories().size()) {
            throw new RuntimeException("One or more categories not found for post: " + postDto.getTitle());
        }

        return Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .likeCount(postDto.getLikeCount())
                .unlikeCount(postDto.getUnlikeCount())
                .dateCreated(postDto.getDateCreated())
                .user(user)
                .postImage(postDto.getPostImage())
                .categories(categories)
                .build();
    }

    @Override
    public PostDto toDto(Post post) {


       if(post == null) {
           return null;
       }
       return PostDto.builder()
               .id(post.getId())
               .title(post.getTitle())
               .content(post.getContent())
               .likeCount(post.getLikeCount())
               .unlikeCount(post.getUnlikeCount())
               .dateCreated(post.getDateCreated())
               .userId(post.getUser().getId())
               .postImage(post.getPostImage())
               .categories(post.getCategories().stream().map(e -> e.getCategoryName()).collect(Collectors.toList()))
               .build();
    }
}
