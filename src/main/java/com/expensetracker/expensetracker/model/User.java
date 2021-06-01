package com.expensetracker.expensetracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

    @Id
    private String userID;
    private String name;
    private String password;
    private String email;

    public User(String userID, String name, String password, String email) {
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.email = email;
    }

}
