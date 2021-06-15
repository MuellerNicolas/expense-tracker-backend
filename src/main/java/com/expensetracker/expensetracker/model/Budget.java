package com.expensetracker.expensetracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model for Budget in Expense-Tracker
 */
@Document(collection = "budgets")
public class Budget {

    @Id
    public String budgetId;
    public String kategorie;
    public Double budget;
    public String userId;

    public Budget(String kategorie, Double budget, String userId) {
        this.kategorie = kategorie;
        this.budget = budget;
        this.userId = userId;
    }

    public String getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(String budgetId) {
        this.budgetId = budgetId;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
