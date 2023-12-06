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
        Optional<User> user = userRepository.findById(postDto.getUser().getId());






        if(postDto == null) {
          return null;
        }
        return Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .likeCount(postDto.getLikeCount())
                .unlikeCount(postDto.getUnlikeCount())
                .dateCreated(postDto.getDateCreated())
                .user(user.get())
                .postImage(postDto.getPostImage())
                .categories(postDto.getCategories().stream().map(e -> categoryRepository.findCategoryByCategoryName(e.getCategoryName())).collect(Collectors.toList()))
                .build();
    }

    @Override
    public PostDto toDto(Post post) {
        List<Category> categories = post.getCategories();

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
               .user(userMapper.toDto(post.getUser()))
               .postImage(post.getPostImage())
               .categories(post.getCategories().stream().map(e -> categoryMapper.toDto(e)).collect(Collectors.toList()))
               .build();
    }
}
