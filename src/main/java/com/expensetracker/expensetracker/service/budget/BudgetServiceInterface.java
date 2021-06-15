package com.expensetracker.expensetracker.service.budget;

import com.expensetracker.expensetracker.model.Budget;

import java.util.List;

/**
 *  Service-Interface for Budget-Tab in Expense-Tracker
 */
public interface BudgetServiceInterface {

    List<Budget> findAllBudgets(String userId);

    Budget saveOrUpdateBudget(Budget budget);


}
