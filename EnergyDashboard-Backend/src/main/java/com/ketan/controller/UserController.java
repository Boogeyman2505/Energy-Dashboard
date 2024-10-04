package com.ketan.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ketan.model.User;
import com.ketan.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/weekly-data")
    public double[] getWeeklyData(@PathVariable String id) {
        return userService.getWeeklyData(id);
    }

    @GetMapping("/{id}/monthly-data")
    public double[] getMonthlyData(@PathVariable String id) {
        return userService.getMonthlyData(id);
    }

    @GetMapping("/{id}/yearly-data")
    public double[] getYearlyData(@PathVariable String id) {
        return userService.getYearlyData(id);
    }

    @PostMapping
    public ResponseEntity<User> createUserWithEnergyRecord(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        Optional<User> existingUserOpt = userService.getUserById(id);

        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setWeeklyData(updatedUser.getWeeklyData());
            existingUser.setMonthlyData(updatedUser.getMonthlyData());
            existingUser.setYearlyData(updatedUser.getYearlyData());

            User savedUser = userService.saveUser(existingUser);
            return ResponseEntity.ok(savedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        Optional<User> existingUserOpt = userService.getUserById(id);

        if (existingUserOpt.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.ok("User Deleted Successfully");
        } else {
            return ResponseEntity.status(404).body("User Not Found");
        }
    }
}