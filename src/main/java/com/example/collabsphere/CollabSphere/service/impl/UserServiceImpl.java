package com.example.collabsphere.CollabSphere.service.impl;

import com.example.collabsphere.CollabSphere.exception.ResourceNotFoundException;
import com.example.collabsphere.CollabSphere.model.mysql.UserMySQL;
import com.example.collabsphere.CollabSphere.repository.mysql.UserMySQLRepository;
import com.example.collabsphere.CollabSphere.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(value = "mySqlTransactionManager")// Ensures all methods in this class are transactional
public class UserServiceImpl implements UserService {

    private final UserMySQLRepository userRepository;

    public UserServiceImpl(UserMySQLRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserMySQL createUser(UserMySQL user) {
        return userRepository.save(user);
    }

    @Override
    public UserMySQL getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    @Override
    public List<UserMySQL> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserMySQL updateUser(Long id, UserMySQL userDetails) {
        UserMySQL existingUser = getUserById(id);
        existingUser.setUsername(userDetails.getUsername());
        existingUser.setEmail(userDetails.getEmail());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        UserMySQL user = getUserById(id);
        userRepository.delete(user);
    }
}
