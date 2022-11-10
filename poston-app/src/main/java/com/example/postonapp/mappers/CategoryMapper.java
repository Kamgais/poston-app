package com.example.postonapp.mappers;

import com.example.postonapp.dtos.CategoryDto;
import com.example.postonapp.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
public class CategoryMapper implements Mapper<Category, CategoryDto>{
    @Override
    public Category toEntity(CategoryDto categoryDto) {
        if(categoryDto == null) {
            return null;
        }
        return Category.builder()
                .categoryName(categoryDto.getCategoryName())
                .categoryIcon(categoryDto.getCategoryIcon())
                .build();
    }

    @Override
    public CategoryDto toDto(Category category) {

        if(category == null) {
            return null;
        }
        return CategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .categoryIcon(category.getCategoryIcon())
                .build();
    }
}
