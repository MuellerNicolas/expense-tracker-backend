package com.expensetracker.expensetracker;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService implements ExpenseServiceInterface {

    @Autowired
    private ExpenseRepository expenseRepository;


    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense saveOrUpdateExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
}
