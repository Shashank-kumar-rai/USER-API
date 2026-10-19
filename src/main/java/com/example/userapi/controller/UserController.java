
package com.example.userapi.controller;

import com.example.userapi.Model.User;
import com.example.userapi.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userrepository;

    // create user
    @PostMapping("/user")
    public User creataUser(@RequestBody User user) {
        return userrepository.save(user);
    }

    // get all users
    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userrepository.findAll();
    }

    // get user by ID
    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id) {
        return userrepository.findById(id).orElse(null);
    }

    // update user
    @PutMapping("/user/{id}")
    public User updateUser(@RequestBody User user, @PathVariable int id) {

        User User = userrepository.findById(id).orElse(null);

        if (User == null) {
            return null;
        }

        User.setName(user.getName());
        User.setCity(user.getCity());
        User.setPassword(user.getPassword());

        return userrepository.save(User);
    }

    // delete user
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable int id) {
        userrepository.deleteById(id);
        return "User deleted successfully";
    }
}
