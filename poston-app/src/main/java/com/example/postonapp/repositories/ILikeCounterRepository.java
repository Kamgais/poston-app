package com.example.postonapp.repositories;

import com.example.postonapp.entities.LikeCounter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = LikeCounter.class, idClass = Long.class)
public interface ILikeCounterRepository extends CrudRepository<LikeCounter,Long> {
}
