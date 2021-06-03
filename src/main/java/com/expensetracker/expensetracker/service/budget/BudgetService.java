package com.expensetracker.expensetracker.service.budget;

import com.expensetracker.expensetracker.model.Budget;
import com.expensetracker.expensetracker.model.dto.BudgetDTO;
import com.expensetracker.expensetracker.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService implements BudgetServiceInterface {

    @Autowired
    BudgetRepository budgetRepository;

    @Override
    public List<Budget> findAllBudgets(String userId) {
        return budgetRepository.findByUserId(userId);
    }

    @Override
    public Budget saveOrUpdateBudget(Budget budget) {
        return budgetRepository.save(budget);
    }
}
