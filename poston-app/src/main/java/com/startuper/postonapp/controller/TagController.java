package com.startuper.postonapp.controller;


import com.startuper.postonapp.dtos.TagDto;
import com.startuper.postonapp.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@CrossOrigin("*")
public class TagController {

    @Autowired
    private TagService tagService;


    @PostMapping("")
    public ResponseEntity<TagDto> createTag(@RequestBody TagDto tagDto) {
        return tagService.saveTag(tagDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<TagDto>> getTagByPostId(@PathVariable("id") Long id) {
        return tagService.getTagByPostId(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<List<TagDto>> updateTags(@PathVariable("id") Long id, @RequestBody List<TagDto> tagDtos) {
        return tagService.updateTags(tagDtos, id);
    }


    @PostMapping("/all")
    public ResponseEntity<List<TagDto>> createTags(@RequestBody List<TagDto> tagDtos) {
       return tagService.saveTags(tagDtos);
    }

}
