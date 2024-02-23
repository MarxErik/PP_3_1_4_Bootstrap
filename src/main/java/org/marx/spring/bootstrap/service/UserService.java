package org.marx.spring.bootstrap.service;

import org.marx.spring.bootstrap.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findByUserEmail(String email);

    List<User> getUserList();

    void create(User user);

    void deleteById(Long userId);

    void update(User updateUser);

    Optional<User> findByUsername(String username);

}