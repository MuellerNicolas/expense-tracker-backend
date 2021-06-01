package com.expensetracker.expensetracker.controller;


import com.expensetracker.expensetracker.model.Budget;
import com.expensetracker.expensetracker.model.Expense;
import com.expensetracker.expensetracker.repository.BudgetRepository;
import com.expensetracker.expensetracker.service.budget.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetRestController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping(value = "/")
    public @ResponseBody List<Budget> getAllBudgets() {
        return budgetService.findAllBudgets("1");
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> saveBudget(@RequestBody Budget budget) {
        budgetService.saveOrUpdateBudget(budget);
        return new ResponseEntity("Budget added successfully", HttpStatus.OK);
    }

    @PutMapping(value = "{kategorieId}")
    public ResponseEntity<?> UpdateExpenseById(@PathVariable("kategorieId") String kategorieId, @RequestBody Budget budget) {
        budgetService.saveOrUpdateBudget(budget);
        return new ResponseEntity("Budget updated successfully", HttpStatus.OK);
    }


}
