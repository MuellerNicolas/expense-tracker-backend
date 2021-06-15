package com.expensetracker.expensetracker.repository;

import com.expensetracker.expensetracker.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository for Expense in Expense-Tracker
 */
public interface ExpenseRepository extends MongoRepository<Expense, String> {

    List<Expense> findTop10ByUserIdOrderByDatumDesc(String userId);

    List<Expense> findByUserId(String userId);

    Expense findByExpenseId(String id);

    @Override
    void deleteById(String id);

}
