package com.startuper.postonapp.services;


import com.startuper.postonapp.dtos.TagDto;
import com.startuper.postonapp.entities.Tag;
import com.startuper.postonapp.mappers.TagMapper;
import com.startuper.postonapp.repositories.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private ITagRepository tagRepository;

    @Autowired
    private TagMapper tagMapper;

    public ResponseEntity<TagDto> saveTag(TagDto tagDto) {

        Tag tag = tagRepository.save(tagMapper.toEntity(tagDto));

        return ResponseEntity.ok().body(tagMapper.toDto(tag));


    }


    public ResponseEntity<List<TagDto>> saveTags(List<TagDto> tagDtos) {
        List<Tag> tags = tagDtos.stream().map(e->tagMapper.toEntity(e)).collect(Collectors.toList());
          tagRepository.saveAll(tags);
        return ResponseEntity.ok().body(tags.stream().map(e->tagMapper.toDto(e)).collect(Collectors.toList()));
    }



    public ResponseEntity<List<TagDto>> updateTags(List<TagDto> tagDtos, Long id) {
        List<Tag> tags = tagRepository.findTagsByPostId(id);
        tags.stream().forEach(e-> tagRepository.deleteById(e.getId()));
        List<Tag> newTags = tagDtos.stream().map(e->tagMapper.toEntity(e)).collect(Collectors.toList());
       return ResponseEntity.ok().body(newTags.stream().map(e-> tagMapper.toDto(e)).collect(Collectors.toList()));
    }


    public ResponseEntity<List<TagDto>> getTagByPostId(Long id) {
       List<Tag> tags = tagRepository.findTagsByPostId(id);

       return ResponseEntity.ok().body(tags.stream().map(e-> tagMapper.toDto(e)).collect(Collectors.toList()));
    }
}
