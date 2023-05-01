package com.liliya.shop.service;

import com.liliya.shop.entity.User;
import com.liliya.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        Optional<User> userById = userRepository.findById(id);
        if (userById.isPresent()) {
            return userRepository.findById(id);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exist");

    }

    public User createNewUser(User user) {
        Optional<User> byId = userRepository.findById(user.getId());
        if (!byId.isPresent()) {
            if (user.getPassword() == null || user.getPassword().equals("")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty password");
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                return userRepository.save(user);
            }

        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User alredy exist");
    }

    public User update(User user) {
        Optional<User> byId = userRepository.findById(user.getId());
        if (byId.isPresent()) {
            if (user.getPassword() == null) {
                user.setPassword(byId.get().getPassword());
            } else if (user.getPassword().equals("")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empty password");
            } else
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exist");
    }

    public void deleteUser(String id) {
        Optional<User> userById = userRepository.findById(id);
        if (userById.isPresent()) {
            userRepository.deleteById(id);

        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User doesn't exist");

    }


}