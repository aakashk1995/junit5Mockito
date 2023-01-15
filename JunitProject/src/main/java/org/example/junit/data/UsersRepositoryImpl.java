package org.example.junit.data;



import org.example.junit.model.User;

import java.util.HashMap;
import java.util.Map;

public class UsersRepositoryImpl implements UsersRepository {

    Map<String, User> users = new HashMap<>();

    @Override
    public boolean save(User user) {

        boolean returnValue = false;

        if(!users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            returnValue = true;
        }

        return returnValue;
    }
}
