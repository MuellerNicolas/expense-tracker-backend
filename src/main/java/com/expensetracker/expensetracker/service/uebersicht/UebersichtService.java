package com.expensetracker.expensetracker.service.uebersicht;


import com.expensetracker.expensetracker.model.Budget;
import com.expensetracker.expensetracker.model.Expense;
import com.expensetracker.expensetracker.model.dto.AusgabeJeKategorieAktuellerMonatDTO;
import com.expensetracker.expensetracker.model.dto.AusgabeJeKategorieHalbesJahrDTO;
import com.expensetracker.expensetracker.repository.BudgetRepository;
import com.expensetracker.expensetracker.repository.ExpenseRepository;
import com.expensetracker.expensetracker.service.erfolge.ErfolgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for Uebersicht-Tab in Expense-Tracker
 */
@Service
public class UebersichtService implements UebersichtServiceInterface {

    // Wire repositories
    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    BudgetRepository budgetRepository;

    /**
     * Get value of expenses per kategorie in the ongoing month
     * @param userId UserId
     * @return Expenses per kategorie in the ongoing month to display
     */
    @Override
    public List<AusgabeJeKategorieAktuellerMonatDTO> findAusgabeJeKategorieAktuellerMonat(String userId) {

        // Initialize target expense per kategorie list
        List<AusgabeJeKategorieAktuellerMonatDTO> ausgabeJeKategorieAktuellerMonatDTOList = new ArrayList<>();

        // Get Local DateTime as starting point for Month Offset Calculation
        LocalDateTime localDateTime = LocalDateTime.now();

        // Buffer Expenses and Budgets
        List<Expense> expenseList = expenseRepository.findByUserId(userId);
        List<Budget> budgetList = budgetRepository.findByUserId(userId);

        // Calcualte value of Expenses for every kategorie in the ongoing month
        for(Budget budget : budgetList) {

            double valueOfExpenses = ErfolgeService.getValueExpensesWithMonthOffset(0, localDateTime, expenseList, budget);

            ausgabeJeKategorieAktuellerMonatDTOList.add(new AusgabeJeKategorieAktuellerMonatDTO(budget.kategorie, valueOfExpenses));
        }
        return ausgabeJeKategorieAktuellerMonatDTOList;
    }

    /**
     * Get value of expenses per kategorie in the last 6 months
     * @param userId UserId
     * @return Expenses per kategorie in the last 6 months to display
     */
    @Override
    public List<AusgabeJeKategorieHalbesJahrDTO> findAusgabeJeKategorieHalbesJahr(String userId) {

        // Initialize target expense list per kategorie in the last 6 months
        List<AusgabeJeKategorieHalbesJahrDTO> ausgabeJeKategorieHalbesJahrDTOList = new ArrayList<>();

        // Get Local DateTime as starting point for Month Offset Calculation
        LocalDateTime localDateTime = LocalDateTime.now();

        // Buffer Expenses and Budgets
        List<Expense> expenseList = expenseRepository.findByUserId(userId);
        List<Budget> budgetList = budgetRepository.findByUserId(userId);

        // Static Date-Formatter, used to manipulate Date-display
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");

        // Calculate  for every kategorie of expenses
        for(Budget budget : budgetList) {

            List<AusgabeJeKategorieAktuellerMonatDTO> ausgabeJeKategorieMonatList = new ArrayList<>();

            // Calculate Value for the last 6 months
            for(int monthOffset = 5; monthOffset >= 0; monthOffset--) {

                double valueOfExpenses = ErfolgeService.getValueExpensesWithMonthOffset(monthOffset, localDateTime, expenseList, budget);

                final LocalDateTime adjustedDateTime = localDateTime.minusMonths(monthOffset);

                ausgabeJeKategorieMonatList.add(new AusgabeJeKategorieAktuellerMonatDTO(adjustedDateTime.format(dateFormatter), valueOfExpenses));
            }
            ausgabeJeKategorieHalbesJahrDTOList.add(new AusgabeJeKategorieHalbesJahrDTO(budget.kategorie, ausgabeJeKategorieMonatList));
        }
        return ausgabeJeKategorieHalbesJahrDTOList;
    }

    /**
     * Get budget-usage per kategorie in the ongoing month in percent
     * @param userId UserId
     * @return Budget-usage per kategorie in the ongoing month in percent to display
     */
    @Override
    public List<AusgabeJeKategorieAktuellerMonatDTO> findBudgetauslastungJeKategorieAktuellerMonat(String userId) {

        // Initialize target usagelist per kategorie in the ongoing month
        List<AusgabeJeKategorieAktuellerMonatDTO> budgetauslastungJeKategorieAktuellerMonatList = new ArrayList<>();

        // Get Local DateTime as starting point for Month Offset Calculation
        LocalDateTime localDateTime = LocalDateTime.now();

        // Buffer Expenses and Budgets
        List<Expense> expenseList = expenseRepository.findByUserId(userId);
        List<Budget> budgetList = budgetRepository.findByUserId(userId);

        // Calculate  for every kategorie of expenses
        for(Budget budget : budgetList) {

            double valueOfExpenses = ErfolgeService.getValueExpensesWithMonthOffset(0, localDateTime, expenseList, budget);

            double percentage = 0d;

            // Calculate budget-usage in percent. If budget is 0, set to full budget-usage.
            if (budget.budget != 0) {
                percentage = valueOfExpenses / budget.budget * 100;
            } else {
                if(valueOfExpenses == 0) {
                    percentage = 100d;
                } else {
                    percentage = 110d;
                }
            }
            budgetauslastungJeKategorieAktuellerMonatList.add(new AusgabeJeKategorieAktuellerMonatDTO(budget.kategorie, percentage));
        }
        return budgetauslastungJeKategorieAktuellerMonatList;
    }


}
