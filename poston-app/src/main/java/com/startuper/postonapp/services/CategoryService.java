package com.startuper.postonapp.services;



import com.startuper.postonapp.dtos.CategoryDto;
import com.startuper.postonapp.entities.Category;
import com.startuper.postonapp.mappers.CategoryMapper;
import com.startuper.postonapp.repositories.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Cacheable("categories")
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        if (!categories.isEmpty()) {
            return categories.stream().map(categoryMapper::toDto).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    @CacheEvict(value = "categories", allEntries = true)
    public void saveCategories(List<Category> categories) {
        categoryRepository.saveAll(categories);
    }
}
