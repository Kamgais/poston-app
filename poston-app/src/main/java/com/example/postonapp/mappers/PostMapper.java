package com.example.postonapp.mappers;

import com.example.postonapp.dtos.PostDto;
import com.example.postonapp.entities.Category;
import com.example.postonapp.entities.Comment;
import com.example.postonapp.entities.Post;
import com.example.postonapp.entities.User;
import com.example.postonapp.repositories.ICategoryRepository;
import com.example.postonapp.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@RequiredArgsConstructor
@Service
public class PostMapper implements  Mapper<Post, PostDto>{



   private final IUserRepository userRepository;

    CategoryMapper categoryMapper = new CategoryMapper();
    UserMapper userMapper = new UserMapper();

    CommentMapper commentMapper = new CommentMapper();


   private final ICategoryRepository categoryRepository;


    @Override
    public Post toEntity(PostDto postDto) {
        Optional<User> user = userRepository.findById(postDto.getUserDto().getId());


        if(postDto == null) {
          return null;
        }
        return Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .postImage(postDto.getPostImage())
                .likeCount(postDto.getLikeCount())
                .unlikeCount(postDto.getUnlikeCount())
                .dateCreated(postDto.getDateCreated())
                .user(user.get())
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
               .postImage(post.getPostImage())
               .likeCount(post.getLikeCount())
               .unlikeCount(post.getUnlikeCount())
               .dateCreated(post.getDateCreated())
               .userDto(userMapper.toDto(post.getUser()))
               .categories(post.getCategories().stream().map(e -> categoryMapper.toDto(e)).collect(Collectors.toList()))
               .build();
    }
}
