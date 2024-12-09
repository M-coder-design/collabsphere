package com.example.collabsphere.CollabSphere.service;

import com.example.collabsphere.CollabSphere.model.mysql.UserMySQL;

import java.util.List;

public interface UserService {
    UserMySQL createUser(UserMySQL user);
    UserMySQL getUserById(Long id);
    List<UserMySQL> getAllUsers();
    UserMySQL updateUser(Long id, UserMySQL userDetails);
    void deleteUser(Long id);
}
