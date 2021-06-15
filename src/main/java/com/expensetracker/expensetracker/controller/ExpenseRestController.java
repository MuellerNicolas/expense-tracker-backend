package com.expensetracker.expensetracker.controller;


import com.expensetracker.expensetracker.model.Expense;
import com.expensetracker.expensetracker.service.expense.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Rest-Service for Ausgaben-Tab in Expense-Tracker
 */
@RestController
@RequestMapping("/api/ausgaben")
public class ExpenseRestController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping(value = "/")
    public @ResponseBody List<Expense> getLatestExpenses() {
        return expenseService.findLatestExpenses("1");
    }

    @GetMapping(value = "{expenseId}")
    public Expense getExpenseById (@PathVariable("expenseId") String expenseId) {
        return expenseService.findExpenseById(expenseId);
    }

    @PostMapping(value = "/")
    public Expense saveExpense(@RequestBody Expense expense) {
        return expenseService.saveOrUpdateExpense(expense);
    }

    @PutMapping(value = "{expenseId}")
    public Expense updateExpenseById(@PathVariable("expenseId") String expenseId, @RequestBody Expense expense) {
        return expenseService.saveOrUpdateExpense(expense);
    }

    @DeleteMapping(value = "{expenseId}")
    public Expense deleteExpenseById (@PathVariable("expenseId") String expenseId) {
        expenseService.deleteExpenseById(expenseId);
        return new Expense(LocalDateTime.now(), "1", 1d, "1", "1" );
    }

}
