package com.startuper.postonapp.repositories;

import com.startuper.postonapp.entities.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.List;

@RepositoryDefinition(domainClass = Image.class , idClass = Long.class)
public interface IImageRepository extends CrudRepository<Image,Long> {

    @Query("SELECT i FROM Image i WHERE i.name = :name")
    Image findImageByName(@Param("name") String name);


    //id = 43

}
