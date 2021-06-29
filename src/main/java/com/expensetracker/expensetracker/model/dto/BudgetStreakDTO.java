package com.expensetracker.expensetracker.model.dto;

import java.util.Objects;

/**
 * DataTypeObject for displaying Budget-Streak for all kategories.
 */
public class BudgetStreakDTO {

    public double monateBudgetStreak;

    public Double getMonateBudgetStreak() {
        return monateBudgetStreak;
    }

    public BudgetStreakDTO(double monateBudgetStreak) {
        this.monateBudgetStreak = monateBudgetStreak;
    }

    public BudgetStreakDTO() {

    }

    public void setMonateBudgetStreak(double monateBudgetStreak) {
        this.monateBudgetStreak = monateBudgetStreak;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetStreakDTO that = (BudgetStreakDTO) o;
        return Double.compare(that.monateBudgetStreak, monateBudgetStreak) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(monateBudgetStreak);
    }
}
