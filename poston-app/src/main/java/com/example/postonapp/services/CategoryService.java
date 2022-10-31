package com.example.postonapp.services;


import com.example.postonapp.entities.Category;
import com.example.postonapp.repositories.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    ICategoryRepository categoryRepository;

    public List<Category> getAllCategories () {
        List<Category> categories = (List<Category>)categoryRepository.findAll();
        if(!categories.isEmpty()) {
            return categories;
        } else {
            return null;
        }
    }
}
