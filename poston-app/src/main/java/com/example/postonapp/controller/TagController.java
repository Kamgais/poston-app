package com.example.postonapp.controller;


import com.example.postonapp.dtos.TagDto;
import com.example.postonapp.services.TagService;
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
        return tagService.saveTag(tagDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<TagDto>> getTagByPostId(Long id) {
        return tagService.getTagByPostId(id);
    }


    @PostMapping("/all")
    public ResponseEntity<List<TagDto>> createTags(@RequestBody List<TagDto> tagDtos) {
       return tagService.saveTags(tagDtos);
    }

}
