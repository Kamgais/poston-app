package com.startuper.postonapp.services;

import com.startuper.postonapp.dtos.TagDto;
import com.startuper.postonapp.entities.Tag;
import com.startuper.postonapp.mappers.TagMapper;
import com.startuper.postonapp.repositories.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private ITagRepository tagRepository;

    @Autowired
    private TagMapper tagMapper;

    @CacheEvict(value = "tagsByPost", key = "#tagDto.postId")
    public TagDto saveTag(TagDto tagDto) {
        Tag tag = tagRepository.save(tagMapper.toEntity(tagDto));
        return tagMapper.toDto(tag);
    }

    @CacheEvict(value = "tagsByPost", allEntries = true)
    public List<TagDto> saveTags(List<TagDto> tagDtos) {
        List<Tag> tags = tagDtos.stream()
                .map(tagMapper::toEntity)
                .collect(Collectors.toList());
        tagRepository.saveAll(tags);
        return tags.stream().map(tagMapper::toDto).collect(Collectors.toList());
    }

    @CacheEvict(value = "tagsByPost", key = "#id")
    public List<TagDto> updateTags(List<TagDto> tagDtos, Long id) {
        List<Tag> tags = tagRepository.findTagsByPostId(id);
        tags.forEach(tag -> tagRepository.deleteById(tag.getId()));
        List<Tag> newTags = tagDtos.stream().map(tagMapper::toEntity).collect(Collectors.toList());
        tagRepository.saveAll(newTags);
        return newTags.stream().map(tagMapper::toDto).collect(Collectors.toList());
    }

    @Cacheable(value = "tagsByPost", key = "#id")
    public List<TagDto> getTagByPostId(Long id) {
        List<Tag> tags = tagRepository.findTagsByPostId(id);
        return tags.stream().map(tagMapper::toDto).collect(Collectors.toList());
    }
}
