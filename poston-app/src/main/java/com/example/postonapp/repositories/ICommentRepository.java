package com.example.postonapp.repositories;


import com.example.postonapp.entities.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
public interface ICommentRepository extends CrudRepository<Comment,Long> {
}
