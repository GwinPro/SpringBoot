package com.jmSpringBoot.crud.dao;

import com.jmSpringBoot.crud.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void deleteUser(long id);
    void updateUser(User user);
    User getUserById(long id);
    User getUserByUserName(String userName);
    List<User> getAllUsers();
}
