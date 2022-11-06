package com.example.postonapp.repositories;


import com.example.postonapp.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

@RepositoryDefinition(domainClass = Category.class, idClass = Long.class)
public interface ICategoryRepository extends CrudRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.categoryName = :name")
    Category findCategoryByCategoryName(@Param("name") String name);

}
