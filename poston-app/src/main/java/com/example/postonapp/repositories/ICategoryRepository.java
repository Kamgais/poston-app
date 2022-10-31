package com.example.postonapp.repositories;


import com.example.postonapp.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Category.class, idClass = Long.class)
public interface ICategoryRepository extends CrudRepository<Category, Long> {
}
