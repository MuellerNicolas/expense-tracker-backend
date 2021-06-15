package com.expensetracker.expensetracker.repository;

import com.expensetracker.expensetracker.model.Budget;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository for Budget in Expense-Tracker
 */
public interface BudgetRepository extends MongoRepository<Budget, String> {

    List<Budget> findByUserId(String userId);

}
