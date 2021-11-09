package com.example.securitydemo.controllers;

import com.example.securitydemo.models.User;
import com.example.securitydemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    //All users
    @GetMapping(value = "/")
    public List<User> getAllUser() {
        return this.userService.getAllUser();
    }

    //return Single user

    @GetMapping(value = "/{username}")
    public User getUser(@PathVariable("username") String username) {
        return this.userService.getUser(username);
    }

    @PostMapping(value = "/adduser")
    public User addUser(@RequestBody User user) {
        return this.userService.addUser(user);
    }

}
