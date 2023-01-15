package org.example.junit.service;


import org.example.junit.model.User;

public interface UserService {
    User createUser(String firstName,
                    String lastName,
                    String email,
                    String password,
                    String repeatPassword);

    String insertUser(User user);
}
