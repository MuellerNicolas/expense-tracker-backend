package com.expensetracker.expensetracker.service;


import com.expensetracker.expensetracker.repository.ExpenseRepository;
import com.expensetracker.expensetracker.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService implements ExpenseServiceInterface {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public List<Expense> findLatestExpenses(String userId) {
        return expenseRepository.findTop10ByUserIdOrderByDatumDesc(userId);
    }

    @Override
    public Expense saveOrUpdateExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Expense findExpenseById( String expenseId ) {
        return expenseRepository.findByExpenseId(expenseId);
    }

    @Override
    public void deleteExpenseById(String expenseId) {
        expenseRepository.deleteById(expenseId);
    };

}
