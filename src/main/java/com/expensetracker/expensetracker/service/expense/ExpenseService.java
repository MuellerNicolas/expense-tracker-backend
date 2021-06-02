package com.expensetracker.expensetracker.service.expense;


import com.expensetracker.expensetracker.repository.ExpenseRepository;
import com.expensetracker.expensetracker.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Expense> findExpenseById(String id ) {
        return expenseRepository.findById(id);
    }

    @Override
    public void deleteExpenseById(String expenseId) {
        expenseRepository.deleteById(expenseId);
    };

}
