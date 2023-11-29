package com.startuper.postonapp.repositories;


import com.startuper.postonapp.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {
    @Autowired
    private IUserRepository userRepository;

    @Test
    public void IUserRepository_SaveAll_ReturnSavedUser() {
        // Arrange
        User user = User.builder()
                .username("postonuser")
                .emailAddress("postonuser@startuper.com")
                .password("poston1!")
                .build();

        // Act
        User savedUser = userRepository.save(user);

        // Assert
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void IUserRepository_GetAll_ReturnMoreThanOneUser() {
        // Arrange
        User user = User.builder()
                .username("postonuser")
                .emailAddress("postonuser@startuper.com")
                .password("poston1!")
                .build();
        User user2 = User.builder()
                .username("postonuser")
                .emailAddress("postonuser@startuper.com")
                .password("poston1!")
                .build();

        userRepository.save(user);
        userRepository.save(user2);

        List<User> userList = (List<User>) userRepository.findAll();

        Assertions.assertThat(userList).isNotNull();
        Assertions.assertThat(userList.size()).isEqualTo(2);
    }


    @Test
    public void IUserRepository_FindById_ReturnUserNotNull() {

        // Arrange
        User user = User.builder()
                .username("postonuser")
                .emailAddress("postonuser@startuper.com")
                .password("poston1!")
                .build();

       User savedUser = userRepository.save(user);

        User userFromDB = userRepository.findById(savedUser.getId()).get();

        // Assertions
        Assertions.assertThat(userFromDB).isNotNull();
    }


    @Test
    public void IUserRepository_FindByEmailAddress_ReturnUserNotNull() {

        // Arrange
        User user = User.builder()
                .username("postonuser")
                .emailAddress("postonuser@startuper.com")
                .password("poston1!")
                .build();

        User savedUser = userRepository.save(user);

        User userFromDB = userRepository.findUserByEmailAddress(savedUser.getEmailAddress()).get();

        // Assertions
        Assertions.assertThat(userFromDB).isNotNull();
    }


    @Test
    public void IUserRepository_UpdateUser_ReturnUserNotNull() {

        // Arrange
        User user = User.builder()
                .username("postonuser")
                .emailAddress("postonuser@startuper.com")
                .password("poston1!")
                .build();

        User savedUser = userRepository.save(user);

        User userFromDB = userRepository.findById(savedUser.getId()).get();
        userFromDB.setUsername("postonuser2");
        userFromDB.setEmailAddress("postonuser2@startuper.com");


        // Act
        User updatedUser = userRepository.save(userFromDB);

        // Assertions
        Assertions.assertThat(updatedUser).isNotNull();
        Assertions.assertThat(updatedUser.getUsername()).isEqualTo("postonuser2");
        Assertions.assertThat(updatedUser.getEmailAddress()).isEqualTo("postonuser2@startuper.com");
    }


    @Test
    public void IUserRepository_DeleteById_ReturnUserNotNull() {

        // Arrange
        User user = User.builder()
                .username("postonuser")
                .emailAddress("postonuser@startuper.com")
                .password("poston1!")
                .build();

        User savedUser = userRepository.save(user);
        User userFromDB = userRepository.findById(savedUser.getId()).get();
        // Act
        userRepository.deleteById(userFromDB.getId());
        Optional<User> userReturn = userRepository.findById(userFromDB.getId());
        // Assertions
        Assertions.assertThat(userReturn).isEmpty();





    }
}
