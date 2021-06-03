package com.expensetracker.expensetracker.model.dto;

public class BudgetDTO {

    public String id;
    public String kategorieName;
    public Double budget;
    public String userId;

    public BudgetDTO(String id, String kategorieName, Double budget, String userId) {
        this.id = id;
        this.kategorieName = kategorieName;
        this.budget = budget;
        this.userId = userId;
    }
}
