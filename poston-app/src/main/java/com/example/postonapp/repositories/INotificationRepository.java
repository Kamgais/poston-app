package com.example.postonapp.repositories;


import com.example.postonapp.entities.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Notification.class, idClass = Long.class)
public interface INotificationRepository extends CrudRepository<Notification,Long> {
}
