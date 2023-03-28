package com.liliya.shop.controller.crud;

import com.liliya.shop.entity.User;
import com.liliya.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = {"/", ""})
    public List<User> userList() {
        return userRepository.findAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Optional<User> readById(@PathVariable(required = true) String id) {
        return userRepository.findById(id);
    }

    @RequestMapping(path = "/new", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public User createUser(@RequestBody User user) {
        return user;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public User update(@RequestBody User user, @PathVariable(required = true) String id) {
        if (!id.equals(user.getId())) {
            throw new IllegalArgumentException("Id is not match!");
        }
        return userRepository.save(user);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(required = true) String id) {

    }


}
