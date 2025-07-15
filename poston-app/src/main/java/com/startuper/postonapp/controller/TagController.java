package com.startuper.postonapp.controller;

import com.startuper.postonapp.dtos.TagDto;
import com.startuper.postonapp.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("")
    public ResponseEntity<TagDto> createTag(@RequestBody TagDto tagDto) {
        TagDto savedTag = tagService.saveTag(tagDto);
        return ResponseEntity.ok(savedTag);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TagDto>> getTagByPostId(@PathVariable("id") Long id) {
        List<TagDto> tags = tagService.getTagByPostId(id);
        return ResponseEntity.ok(tags);
    }

    @PutMapping("/{id}")
    public ResponseEntity<List<TagDto>> updateTags(@PathVariable("id") Long id, @RequestBody List<TagDto> tagDtos) {
        List<TagDto> updatedTags = tagService.updateTags(tagDtos, id);
        return ResponseEntity.ok(updatedTags);
    }

    @PostMapping("/all")
    public ResponseEntity<List<TagDto>> createTags(@RequestBody List<TagDto> tagDtos) {
        List<TagDto> savedTags = tagService.saveTags(tagDtos);
        return ResponseEntity.ok(savedTags);
    }
}
