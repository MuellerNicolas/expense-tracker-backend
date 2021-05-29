package com.expensetracker.expensetracker;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ausgabe")
public class ExpenseRestController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping(value = "/")
    public List<Expense> getAllExpenses() {
        return expenseService.findLatestExpenses();
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveOrUpdateExpense(@RequestBody Expense expense) {
        expenseService.saveOrUpdateExpense(expense);
        return new ResponseEntity("Expense added successfully", HttpStatus.OK);
    }



}
