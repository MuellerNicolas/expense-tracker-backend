package com.expensetracker.expensetracker;

import java.util.List;

public interface ExpenseServiceInterface {

    List<Expense> findAll();

    Expense saveOrUpdateExpense(Expense expense);

}
