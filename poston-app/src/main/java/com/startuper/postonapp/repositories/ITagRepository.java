package com.startuper.postonapp.repositories;


import com.startuper.postonapp.entities.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.List;

@RepositoryDefinition(domainClass = Tag.class, idClass = Long.class)
public interface ITagRepository extends CrudRepository<Tag,Long> {

    @Query("SELECT t FROM Tag t WHERE t.post.id =:id")
    List<Tag> findTagsByPostId(@Param("id") Long id);

    @Query("SELECT t FROM Tag t WHERE t.user.id =:id")
    List<Tag> findTagsByUserId(@Param("id") Long id);
}
