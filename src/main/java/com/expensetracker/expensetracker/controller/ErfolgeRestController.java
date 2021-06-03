package com.expensetracker.expensetracker.controller;

import com.expensetracker.expensetracker.model.dto.BadgesDTO;
import com.expensetracker.expensetracker.model.dto.BudgetStreakDTO;
import com.expensetracker.expensetracker.service.erfolge.ErfolgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ErfolgeRestController {

    @Autowired
    private ErfolgeService erfolgeService;


    @GetMapping(value = "/budgetStreak")
    public @ResponseBody BudgetStreakDTO getBudgetStreak() {
        return erfolgeService.findBudgetStreak("1");
    }

    @GetMapping(value = "/badges")
    public @ResponseBody List<BadgesDTO> getBadges() {
        return erfolgeService.findBadges("1");
    }

}
