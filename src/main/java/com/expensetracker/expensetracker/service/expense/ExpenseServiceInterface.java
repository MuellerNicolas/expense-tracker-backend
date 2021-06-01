package com.expensetracker.expensetracker.service.expense;

import com.expensetracker.expensetracker.model.Expense;

import java.util.List;

public interface ExpenseServiceInterface {

    List<Expense> findLatestExpenses(String userId);

    Expense saveOrUpdateExpense(Expense expense);

    Expense findExpenseById(String expenseId);

    void deleteExpenseById(String expenseId);
}
