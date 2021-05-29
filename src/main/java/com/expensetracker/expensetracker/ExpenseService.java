package com.expensetracker.expensetracker;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService implements ExpenseServiceInterface {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public List<Expense> findLatestExpenses() {
        return expenseRepository.findTop10ByOrderByDatumDesc();
    }

    @Override
    public Expense saveOrUpdateExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
}
