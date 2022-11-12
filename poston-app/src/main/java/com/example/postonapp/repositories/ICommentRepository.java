package com.example.postonapp.repositories;


import com.example.postonapp.entities.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.List;

@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface ICommentRepository extends CrudRepository<Comment,Long> {

    @Query("SELECT c FROM Comment c WHERE c.post.id = :id")
    List<Comment> findCommentByPostId(@Param("id") Long id);

    @Query("SELECT COUNT (c) FROM Comment c WHERE c.post.id = :id")
    Long countCommentByPostId(@Param("id") Long id);
}
