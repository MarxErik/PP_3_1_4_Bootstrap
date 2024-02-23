package org.marx.spring.bootstrap.service;

import org.marx.spring.bootstrap.model.User;
import org.marx.spring.bootstrap.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByUserEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void create(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public void update(User updateUser) {
//
        if (userRepository.findById(updateUser.getId()).isPresent()) {
            updateUser.setPassword(bCryptPasswordEncoder.encode(updateUser.getPassword()));
            userRepository.save(updateUser);
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}