package com.liliya.shop.controller.crud;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public void login(@RequestParam(name = "username") String userName, @RequestParam String password) {
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public void logout() {
    }
}
