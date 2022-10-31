package com.example.postonapp.services;


import com.example.postonapp.entities.User;
import com.example.postonapp.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    IUserRepository userRepository;


    /*
    * create a account
     */
    public User createAUser(User user) {
        User newUser = null;
        if (emailExist(user.getEmailAddress())) {
            return null;
        }

        newUser = null;
        try {

            newUser = userRepository.save(user);
        } catch (Error error) {
            System.out.println(error);
        }
        return newUser;
    }

    /*
    * exist email
     */
    public boolean emailExist(String email) {
        User user = userRepository.findUserByEmailAddress(email);
        if(user != null) {
            return true;
        } else {
            return false;
        }

    }

    /*
    * login email
     */
    public User loginAUser(User user) {
        User fetchUser = userRepository.findUserByEmailAddress(user.getEmailAddress());
        if(fetchUser != null) {
            String fetchPasswort = fetchUser.getPassword();
            if(fetchPasswort == user.getPassword()) {
                return fetchUser;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
