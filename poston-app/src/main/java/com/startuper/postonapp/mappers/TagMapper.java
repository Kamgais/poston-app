package com.startuper.postonapp.mappers;

import com.startuper.postonapp.dtos.TagDto;
import com.startuper.postonapp.entities.Post;
import com.startuper.postonapp.entities.Tag;
import com.startuper.postonapp.repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class TagMapper implements Mapper<Tag, TagDto>{


    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IPostRepository postRepository;

    @Override
    public Tag toEntity(TagDto tagDto) {
        if(tagDto == null) {
            return null;
        } else {
            Optional<Post> post = postRepository.findById(tagDto.getPost().getId());
           return  Tag.builder()
                    .post(post.get())
                    .user(userMapper.toEntity(tagDto.getUser()))
                    .build();
        }

    }

    @Override
    public TagDto toDto(Tag tag) {
        if(tag == null) {
            return null;
        } else {
            return TagDto.builder()
                    .id(tag.getId())
                    .post(postMapper.toDto(tag.getPost()))
                    .user(userMapper.toDto(tag.getUser()))
                    .build();
        }

    }
}
