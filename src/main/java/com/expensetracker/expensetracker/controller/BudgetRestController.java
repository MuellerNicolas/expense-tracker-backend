package com.expensetracker.expensetracker.controller;


import com.expensetracker.expensetracker.model.Budget;
import com.expensetracker.expensetracker.service.budget.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest-Service for Budget-Tab in Expense-Tracker
 */
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
    public Budget saveBudget(@RequestBody Budget budget) {
        return budgetService.saveOrUpdateBudget(budget);
    }

    @PutMapping(value = "{kategorieId}")
    public Budget UpdateExpenseById(@PathVariable("kategorieId") String kategorieId, @RequestBody Budget budget) {
        return budgetService.saveOrUpdateBudget(budget);
    }


}
