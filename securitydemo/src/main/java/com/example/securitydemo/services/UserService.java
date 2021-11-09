package com.example.securitydemo.services;

import com.example.securitydemo.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    List<User> userList = new ArrayList<>();

    public  UserService(){
        userList.add(new User("root", "root", "root@xyz.com"));
        userList.add(new User("abc", "abc", "abc@xyz.com"));
        userList.add(new User("python", "py", "pbc@xyz.com"));
        userList.add(new User("home", "hm", "home@xyz.com"));
        userList.add(new User("js", "javascript", "jsa@xyz.com"));
    }

    //get all user
    public List<User> getAllUser() {
        return this.userList;
    }

    //get single user
    public User getUser(String username) {
        return this.userList.stream().filter((user)->user.getUsername().equals(username)).findAny().orElse(null);
    }

    //Add user
    public User addUser(User user){
        this.userList.add(user);
        return user;
    }
    
}/**/
