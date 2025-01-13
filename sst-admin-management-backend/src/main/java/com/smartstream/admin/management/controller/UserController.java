package com.smartstream.admin.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartstream.admin.management.exception.ResourceNotFoundException;
import com.smartstream.admin.management.model.AppUser;
import com.smartstream.admin.management.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userServiceRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<AppUser> getAllUsers() {
        return userServiceRepository.getAllUsers();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        AppUser user = userServiceRepository.getUserById(id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<AppUser> createUser(@Valid @RequestBody AppUser user) {
        AppUser createdUser = userServiceRepository.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable Long id, @Valid @RequestBody AppUser user) {
        AppUser updatedUser = userServiceRepository.updateUser(id, user);
        if (updatedUser == null) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userServiceRepository.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
