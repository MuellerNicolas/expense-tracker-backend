package com.expensetracker.expensetracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Model for User in Expense-Tracker
 */
@Document(collection = "users")
public class User {

    @Id
    private String userId;
    private String name;
    private String password;
    private String email;
    private LocalDateTime datumCreated;

    public User(String userId, String name, String password, String email, LocalDateTime datumCreated) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.datumCreated = datumCreated;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDatumCreated() {
        return datumCreated;
    }

    public void setDatumCreated(LocalDateTime datumCreated) {
        this.datumCreated = datumCreated;
    }
}
