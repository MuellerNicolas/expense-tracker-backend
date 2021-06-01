package com.expensetracker.expensetracker.controller;


import com.expensetracker.expensetracker.model.Expense;
import com.expensetracker.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> saveExpense(@RequestBody Expense expense) {
        expenseService.saveOrUpdateExpense(expense);
        return new ResponseEntity("Expense added successfully", HttpStatus.OK);
    }

    @PutMapping(value = "{expenseId}")
    public ResponseEntity<?> UpdateExpenseById(@PathVariable("expenseId") String expenseId, @RequestBody Expense expense) {
        expenseService.saveOrUpdateExpense(expense);
        return new ResponseEntity("Expense updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "{expenseId}")
    public ResponseEntity<?> deleteExpenseById (@PathVariable("expenseId") String expenseId) {
        expenseService.deleteExpenseById(expenseId);
        return new ResponseEntity("Expense deleted successfully", HttpStatus.OK);
    }

}
