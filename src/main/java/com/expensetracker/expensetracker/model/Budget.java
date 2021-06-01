package com.expensetracker.expensetracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "budgets")
public class Budget {

    @Id
    public String kategorieId;
    public String kategorieName;
    public Double budget;
    public String userId;

    public Budget(String kategorieId, String kategorieName, Double budget, String userId) {
        this.kategorieId = kategorieId;
        this.kategorieName = kategorieName;
        this.budget = budget;
        this.userId = userId;
    }


}
