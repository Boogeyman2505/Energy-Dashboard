package com.ketan.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String email;
    private double[] weeklyData;
    private double[] monthlyData;
    private double[] yearlyData;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double[] getWeeklyData() {
        return weeklyData;
    }

    public void setWeeklyData(double[] weeklyData) {
        this.weeklyData = weeklyData;
    }

    public double[] getMonthlyData() {
        return monthlyData;
    }

    public void setMonthlyData(double[] monthlyData) {
        this.monthlyData = monthlyData;
    }

    public double[] getYearlyData() {
        return yearlyData;
    }

    public void setYearlyData(double[] yearlyData) {
        this.yearlyData = yearlyData;
    }
}