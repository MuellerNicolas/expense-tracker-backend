package com.expensetracker.expensetracker.service.expense;


import com.expensetracker.expensetracker.repository.ExpenseRepository;
import com.expensetracker.expensetracker.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for Expense-Tab in Expense-Tracker
 */
@Service
public class ExpenseService implements ExpenseServiceInterface {

    // Wire repositories
    @Autowired
    private ExpenseRepository expenseRepository;

    /**
     * Get all expenes from database
     * @param userId UserId
     * @return List of all expenses to display
     */
    @Override
    public List<Expense> findLatestExpenses(String userId) {
        return expenseRepository.findByUserIdOrderByDatumDesc(userId);
    }

    /**
     * Save or update expense to database
     * @param expense Expense
     * @return Saved Expense
     */
    @Override
    public Expense saveOrUpdateExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    /**
     * Find Expense by expenseId from database
     * @param id expenseId
     * @return Found Expense to display
     */
    @Override
    public Expense findExpenseById(String id ) {
        return expenseRepository.findByExpenseId(id);
    }

    /**
     * Delete Expense by expenseId from database
     * @param expenseId expenseId
     */
    @Override
    public void deleteExpenseById(String expenseId) {
        expenseRepository.deleteById(expenseId);
    };

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

}
