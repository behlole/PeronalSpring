package com.example.spingrest.Controllers;

import com.example.spingrest.Models.User;
import com.example.spingrest.Repo.UserRepository;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping(value = {"/", ""})
    public ResponseEntity<?> addUser(@RequestBody User user) {
        User created_user = userRepository.insert(user);
        return new ResponseEntity<>(created_user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        User result = userRepository.deleteUser(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        User user = userRepository.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String id) {
        String response = userRepository.update(user,id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = {"", "/", "/list"})
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(userRepository.getAllUsers(), HttpStatus.OK);
    }
}
