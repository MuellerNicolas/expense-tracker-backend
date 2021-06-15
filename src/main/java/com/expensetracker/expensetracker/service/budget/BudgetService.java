package com.expensetracker.expensetracker.service.budget;

import com.expensetracker.expensetracker.model.Budget;
import com.expensetracker.expensetracker.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for Budget-Tab in Expense-Tracker
 */
@Service
public class BudgetService implements BudgetServiceInterface {

    // Wire repositories
    @Autowired
    BudgetRepository budgetRepository;

    /**
     * Find all budgets for kategories from the database
     * @param userId UserId
     * @return List of all budgets to display
     */
    @Override
    public List<Budget> findAllBudgets(String userId) {
        return budgetRepository.findByUserId(userId);
    }

    /**
     * Save or Update budgets in the database
     * @param budget Budget
     * @return Saved Budget
     */
    @Override
    public Budget saveOrUpdateBudget(Budget budget) {
        return budgetRepository.save(budget);
    }
}
