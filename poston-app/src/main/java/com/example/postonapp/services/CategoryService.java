package com.example.postonapp.services;


import com.example.postonapp.dtos.CategoryDto;
import com.example.postonapp.entities.Category;
import com.example.postonapp.mappers.CategoryMapper;
import com.example.postonapp.repositories.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    ICategoryRepository categoryRepository;


    @Autowired
    CategoryMapper categoryMapper;

    public ResponseEntity<List<CategoryDto>> getAllCategories () {
        List<Category> categories = (List<Category>)categoryRepository.findAll();
        if(!categories.isEmpty()) {
            List<CategoryDto> categoryDtos = categories.stream().map(e -> categoryMapper.toDto(e)).collect(Collectors.toList());
            return ResponseEntity.ok().body(categoryDtos);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
