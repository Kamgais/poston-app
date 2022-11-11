package com.example.postonapp.repositories;


import com.example.postonapp.entities.Category;
import com.example.postonapp.entities.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.List;

@RepositoryDefinition(domainClass = Post.class, idClass = Long.class)
public interface IPostRepository extends CrudRepository<Post,Long> {


   @Query("SELECT p FROM Post p  WHERE p.title LIKE :title%")
   List<Post> findPostByTitle(@Param("title") String title);
}
