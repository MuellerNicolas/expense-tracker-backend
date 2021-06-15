package com.expensetracker.expensetracker.repository;

import com.expensetracker.expensetracker.model.Expense;
import com.expensetracker.expensetracker.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository for User in Expense-Tracker
 */
public interface UserRepository extends MongoRepository<Expense, String> {

    User findByUserId(String userId);

}
