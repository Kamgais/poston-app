package com.startuper.postonapp.repositories;



import com.startuper.postonapp.entities.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface IUserRepository extends CrudRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.emailAddress = :email ")
    Optional<User> findUserByEmailAddress(@Param("email") String email);


    @Query("SELECT u FROM User u WHERE u.username LIKE :username% ")
    List<User> findUsersByUsername(@Param("username") String username);

}
