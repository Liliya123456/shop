package com.liliya.shop.controller.crud;

import com.liliya.shop.entity.User;
import com.liliya.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = {"/", ""})
    public List<User> userList() {
        return userRepository.findAll();
    }
}
