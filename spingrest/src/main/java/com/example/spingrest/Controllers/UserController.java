package com.example.spingrest.Controllers;

import com.example.spingrest.Models.User;
import com.example.spingrest.Repo.UserRepository;
import com.example.spingrest.Utilities.ResponseBody;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
//@PreAuthorize("hasRole(ADMIN)")
public class UserController {
    @Autowired
    UserRepository userRepository;


    ResponseBody responseBody = new ResponseBody();

    @PostMapping(value = {"/", ""})
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            setResponse(userRepository.insert(user), "User Created Successfully", true);
        } catch (Exception e) {
            setResponse(null, e.getMessage(), false);
        }
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try {
            setResponse(userRepository.deleteUser(id), "User Deleted Successfully", true);
        } catch (Exception e) {
            setResponse(null, e.getMessage(), false);
        }

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        try {
            setResponse(userRepository.getUserById(id), "User fetched successfully", true);
        } catch (Exception e) {
            setResponse(null, e.getMessage(), false);
        }
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String id) {
        try {
            String updated_user = userRepository.update(user, id);
            setResponse(userRepository.getUserById(id), updated_user, true);
        } catch (Exception e) {
            setResponse(null, e.getMessage(), false);
        }
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping(value = {"", "/", "/list"})
    public ResponseEntity<?> getUsers() {
        try {
            setResponse(userRepository.getAllUsers(), "User Fetched Successfully", true);
        } catch (Exception e) {
            setResponse(null, e.getMessage(), false);
        }
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    private void setResponse(Object data, String message, Boolean success) {
        responseBody.setData(data);
        responseBody.setSuccess(success);
        responseBody.setMessage(message);
    }
}
