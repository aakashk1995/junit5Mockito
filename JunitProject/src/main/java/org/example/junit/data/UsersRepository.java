package org.example.junit.data;


import org.example.junit.model.User;

public interface UsersRepository {
    boolean save(User user);

    void insert(User user);
}
