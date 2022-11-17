package com.example.postonapp.repositories;


import com.example.postonapp.entities.Post;
import com.example.postonapp.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.List;


@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface IUserRepository extends CrudRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.emailAddress = :email ")
    User findUserByEmailAddress(@Param("email") String email);


    @Query("SELECT u FROM User u WHERE u.username LIKE :username% ")
    List<User> findUsersByUsername(@Param("username") String username);

}
