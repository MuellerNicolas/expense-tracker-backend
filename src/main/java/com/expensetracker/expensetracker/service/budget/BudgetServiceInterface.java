package com.expensetracker.expensetracker.service.budget;

import com.expensetracker.expensetracker.model.Budget;

import java.util.List;

public interface BudgetServiceInterface {

    List<Budget> findAllBudgets(String userId);

    Budget saveOrUpdateBudget(Budget budget);


}
