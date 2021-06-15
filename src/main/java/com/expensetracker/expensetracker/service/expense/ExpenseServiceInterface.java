package com.expensetracker.expensetracker.service.expense;

import com.expensetracker.expensetracker.model.Expense;

import java.util.List;
import java.util.Optional;

/**
 *  Service-Interface for Expense-Tab in Expense-Tracker
 */
public interface ExpenseServiceInterface {

    List<Expense> findLatestExpenses(String userId);

    Expense saveOrUpdateExpense(Expense expense);

    Expense findExpenseById(String expenseId);

    void deleteExpenseById(String expenseId);
}
