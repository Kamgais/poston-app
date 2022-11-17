package com.example.postonapp.mappers;

import com.example.postonapp.dtos.TagDto;
import com.example.postonapp.entities.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TagMapper implements Mapper<Tag, TagDto>{


    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Tag toEntity(TagDto tagDto) {
        if(tagDto == null) {
            return null;
        } else {
           return  Tag.builder()
                    .post(postMapper.toEntity(tagDto.getPost()))
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
