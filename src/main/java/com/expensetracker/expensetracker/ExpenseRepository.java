package com.expensetracker.expensetracker;


import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExpenseRepository extends MongoRepository<Expense, String> {

    List<Expense> findTop10ByOrderByDatumDesc();


}
