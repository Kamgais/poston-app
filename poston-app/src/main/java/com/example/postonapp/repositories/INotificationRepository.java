package com.example.postonapp.repositories;


import com.example.postonapp.entities.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.List;

@RepositoryDefinition(domainClass = Notification.class, idClass = Long.class)
public interface INotificationRepository extends CrudRepository<Notification,Long> {

    @Query("SELECT n FROM Notification n WHERE  n.post.user.id =:userId")
    List<Notification> findAllByUserId(@Param("userId") Long userId);


    @Query("SELECT n FROM Notification n WHERE n.post.id =:postId")
    List<Notification> findAllByPostId(@Param("postId") Long id );
}
