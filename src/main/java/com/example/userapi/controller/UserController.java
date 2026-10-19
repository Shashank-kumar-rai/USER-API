
package com.example.userapi.controller;

import com.example.userapi.Model.User;
import com.example.userapi.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userrepository;

    // create user
    @PostMapping("/user")
    public ResponseEntity<User> creataUser(@RequestBody User user) {

        User save = userrepository.save(user);
        return  ResponseEntity.status(HttpStatus.CREATED).body(save);

    }

    // get all users
    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userrepository.findAll();
        return ResponseEntity.ok(users);
    }

    // get user by ID
    @GetMapping("/user/{id}")
    public ResponseEntity<Object>getUserById(@PathVariable int id) {
        User user = userrepository.findById(id).orElse(null);
        if(user !=null) {

            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not available");
    }

    // update user

    @PutMapping("/user/{id}")
    public ResponseEntity<Object> updateUser(
            @RequestBody User user,
            @PathVariable int id) {

        User existingUser = userrepository.findById(id).orElse(null);

        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not available");
        }

        existingUser.setName(user.getName());
        existingUser.setCity(user.getCity());
        existingUser.setPassword(user.getPassword());

        User updatedUser = userrepository.save(existingUser);

        return ResponseEntity.ok(updatedUser);
    }


    // delete user
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {

        if (userrepository.existsById(id)) {
            userrepository.deleteById(id);

            return ResponseEntity.ok("User deleted successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User not found");
    }
}
