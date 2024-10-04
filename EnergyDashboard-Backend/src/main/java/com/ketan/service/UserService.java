package com.ketan.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ketan.model.User;
import com.ketan.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public double[] getWeeklyData(String userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(User::getWeeklyData).orElse(new double[0]);
    }

    public double[] getMonthlyData(String userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(User::getMonthlyData).orElse(new double[0]);
    }

    public double[] getYearlyData(String userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(User::getYearlyData).orElse(new double[0]);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}