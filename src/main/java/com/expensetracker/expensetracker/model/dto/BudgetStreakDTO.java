package com.expensetracker.expensetracker.model.dto;

public class BudgetStreakDTO {

    public double monateBudgetStreak;

    public BudgetStreakDTO(Double monateBudgetStreak) {
        this.monateBudgetStreak = monateBudgetStreak;
    }

    public Double getMonateBudgetStreak() {
        return monateBudgetStreak;
    }

    public void setMonateBudgetStreak(double monateBudgetStreak) {
        this.monateBudgetStreak = monateBudgetStreak;
    }
}
