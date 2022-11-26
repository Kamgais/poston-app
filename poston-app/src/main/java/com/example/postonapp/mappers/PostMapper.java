package com.example.postonapp.mappers;

import com.example.postonapp.dtos.PostDto;
import com.example.postonapp.entities.*;
import com.example.postonapp.repositories.ICategoryRepository;
import com.example.postonapp.repositories.IImageRepository;
import com.example.postonapp.repositories.IUserRepository;
import com.example.postonapp.services.ImageService;
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
    private final IImageRepository imageRepository;


    @Autowired
    CategoryMapper categoryMapper ;

    @Autowired
    UserMapper userMapper ;

    @Autowired
    ImageMapper imageMapper;


    @Autowired
    private ImageService imageService;







    @Override
    public Post toEntity(PostDto postDto) {
        Optional<User> user = userRepository.findById(postDto.getUser().getId());
        Optional<Image> image = imageRepository.findById(postDto.getImage().getId());





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
                .image(image.get())
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
               .image(imageService.getImage(post.getImage().getName()))
               .categories(post.getCategories().stream().map(e -> categoryMapper.toDto(e)).collect(Collectors.toList()))
               .build();
    }
}
