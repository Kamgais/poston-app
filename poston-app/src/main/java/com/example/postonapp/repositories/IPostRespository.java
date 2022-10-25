package com.example.postonapp.repositories;


import com.example.postonapp.entities.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;


@RepositoryDefinition(domainClass = Post.class, idClass = Long.class)
public interface IPostRespository extends CrudRepository<Post,Long> {
}
