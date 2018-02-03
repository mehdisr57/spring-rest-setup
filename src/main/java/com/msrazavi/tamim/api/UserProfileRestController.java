package com.msrazavi.tamim.api;

import com.msrazavi.tamim.model.UserProfileEntity;
import com.msrazavi.tamim.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Mehdi on 2/1/2018.
 */

@RestController
public class UserProfileRestController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    // Get All user
    @GetMapping("/api/profile")
    public ResponseEntity<List<UserProfileEntity>> list() {
        return ResponseEntity.ok().body(userProfileRepository.findAll());
    }

    // Create a new User
    @PostMapping("/api/profile")
    public ResponseEntity<?> save(@Valid @RequestBody UserProfileEntity user) {
        UserProfileEntity saved = userProfileRepository.save(user);
        return ResponseEntity.ok().body("New User has been saved with ID:" + saved.getId());
    }

    // Get a Single User
    @GetMapping("/api/profile/{id}")
    public ResponseEntity<UserProfileEntity> get(@PathVariable(value = "id") Long userId) {
        UserProfileEntity user = userProfileRepository.findOne(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    // Update a User
    @PutMapping("/api/profile/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long userId,
                                    @Valid @RequestBody UserProfileEntity user) {
        UserProfileEntity oldUser = userProfileRepository.findOne(userId);
        if (oldUser == null) {
            return ResponseEntity.notFound().build();
        }

        UserProfileEntity updatedUser = userProfileRepository.save(user);
        return ResponseEntity.ok().body("User has been updated successfully.");
    }

    // Delete a User
    @DeleteMapping("/api/profile/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
        UserProfileEntity user = userProfileRepository.findOne(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        userProfileRepository.delete(user);
        return ResponseEntity.ok().body("User has been deleted successfully.");
    }
}
