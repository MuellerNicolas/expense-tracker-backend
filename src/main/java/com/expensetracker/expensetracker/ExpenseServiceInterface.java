package com.expensetracker.expensetracker;

import java.util.List;

public interface ExpenseServiceInterface {

    List<Expense> findLatestExpenses();

    Expense saveOrUpdateExpense(Expense expense);

}
