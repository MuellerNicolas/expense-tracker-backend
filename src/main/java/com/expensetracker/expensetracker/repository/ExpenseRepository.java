package com.expensetracker.expensetracker.repository;

import com.expensetracker.expensetracker.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface ExpenseRepository extends MongoRepository<Expense, String> {

    List<Expense> findTop10ByUserIdOrderByDatumDesc(String userId);

    List<Expense> findByUserId(String userId);

    Expense findByExpenseId(String expenseId);

    @Override
    void deleteById(String expenseId);

}
