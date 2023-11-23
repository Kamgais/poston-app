package com.startuper.postonapp.services;



import com.startuper.postonapp.dtos.CategoryDto;
import com.startuper.postonapp.entities.Category;
import com.startuper.postonapp.mappers.CategoryMapper;
import com.startuper.postonapp.repositories.ICategoryRepository;
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
