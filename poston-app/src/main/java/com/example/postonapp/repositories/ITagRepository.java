package com.example.postonapp.repositories;


import com.example.postonapp.entities.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Tag.class, idClass = Long.class)
public interface ITagRepository extends CrudRepository<Tag,Long> {
}
