package com.msrazavi.tamim.api;

import com.msrazavi.tamim.model.UserEntity;
import com.msrazavi.tamim.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Mehdi on 2/1/2018.
 */

@RestController
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    // Get All user
    @GetMapping("/api/users")
    public ResponseEntity<List<UserEntity>> list() {
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    // Create a new User
    @PostMapping("/api/users")
    public ResponseEntity<?> save(@Valid @RequestBody UserEntity user) {
        UserEntity saved = userRepository.save(user);
        return ResponseEntity.ok().body("New User has been saved with ID:" + saved.getId());
    }

    // Get a Single User
    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable(value = "id") Long userId) {
        UserEntity user = userRepository.findOne(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    // Update a User
    @PutMapping("/api/users/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long userId,
                                    @Valid @RequestBody UserEntity user) {
        UserEntity oldUser = userRepository.findOne(userId);
        if (oldUser == null) {
            return ResponseEntity.notFound().build();
        }

        UserEntity updatedUser = userRepository.save(user);
        return ResponseEntity.ok().body("User has been updated successfully.");
    }

    // Delete a User
    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        UserEntity user = userRepository.findOne(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        userRepository.delete(user);
        return ResponseEntity.ok().body("User has been deleted successfully.");
    }
}
