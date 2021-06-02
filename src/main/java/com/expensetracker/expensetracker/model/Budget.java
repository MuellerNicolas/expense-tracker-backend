package com.expensetracker.expensetracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "budgets")
public class Budget {

    @Id
    public String kategorie;
    public Double budget;
    public String userId;

    public Budget(String kategorie, Double budget, String userId) {
        this.kategorie = kategorie;
        this.budget = budget;
        this.userId = userId;
    }


}
