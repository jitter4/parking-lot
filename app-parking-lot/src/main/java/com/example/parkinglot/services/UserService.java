package com.example.parkinglot.services;

import com.example.parkinglot.entities.User;

import java.util.Optional;

public interface UserService {

    boolean existsByUsername(String username);

    User save(User user);

    boolean existsByEmail(String username);

    Optional<User> findByUsername(String username);

}
