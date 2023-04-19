package com.liliya.shop.service;

import com.liliya.shop.entity.User;
import com.liliya.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public List<User> userList() {
        return userRepository.findAll();
    }

    public Optional<User> readById(String id) {
        return userRepository.findById(id);
    }

    public User createNewUser(User user) {
        if (user.getPassword() == null || user.getPassword().equals("")) {
            throw new IllegalArgumentException("Empty password"); //TODO Respond with 400
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
    }

    public User update(User user) {
        Optional<User> byId = userRepository.findById(user.getId());
        if (byId.isPresent()) {
            if (user.getPassword() == null) {
                user.setPassword(byId.get().getPassword());
            } else if (user.getPassword().equals("")) {
                throw new IllegalArgumentException("Empty password"); //TODO Respond with 400
            } else
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        throw new IllegalArgumentException("Did not find user");
    }

    public void deleteUser(String id) {
        Optional<User> userById = userRepository.findById(id);
        if (userById.isPresent()) {
            userRepository.deleteById(id);

        } else throw new IllegalArgumentException("Did not find user");

    }


}