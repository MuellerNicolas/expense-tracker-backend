package com.expensetracker.expensetracker.model.dto;

/**
 * DataTypeObject for displaying Budget-Streak for all kategories.
 */
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
