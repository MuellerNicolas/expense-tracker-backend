package com.expensetracker.expensetracker.service.erfolge;

import com.expensetracker.expensetracker.model.Budget;
import com.expensetracker.expensetracker.model.Expense;
import com.expensetracker.expensetracker.model.dto.AusgabeJeKategorieAktuellerMonatDTO;
import com.expensetracker.expensetracker.model.dto.AusgabeJeKategorieHalbesJahrDTO;
import com.expensetracker.expensetracker.model.dto.BadgesDTO;
import com.expensetracker.expensetracker.model.dto.BudgetStreakDTO;
import com.expensetracker.expensetracker.repository.BudgetRepository;
import com.expensetracker.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ErfolgeService implements ErfolgeServiceInterface {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    BudgetRepository budgetRepository;

    @Override
    public List<BadgesDTO> findBadges(String userId) {

        List<BadgesDTO> badgesDTOList = new ArrayList<>();

        LocalDateTime localDateTime = LocalDateTime.now();

        List<Expense> expenseList = expenseRepository.findByUserId("1");
        List<Budget> budgetList = budgetRepository.findByUserId("1");


        for(Budget budget : budgetList) {

            double monateEingehaltenTotal = 0;

            for(int monthOffset = 0; monthOffset < 24; monthOffset++) {

                final LocalDateTime adjustedDateTime = localDateTime.minusMonths(monthOffset);

                List<Expense> adjustedMonthYearList = expenseList.stream().filter(expense -> expense.datum.getYear() == adjustedDateTime.getYear() && expense.datum.getMonth() == adjustedDateTime.getMonth()).collect(Collectors.toList());

                List<Expense> ongoingMonthYearFilteredByKategorieList = adjustedMonthYearList.stream().filter(expense -> expense.kategorie.equals(budget.kategorie)).collect(Collectors.toList());
                double value = ongoingMonthYearFilteredByKategorieList.stream().map(expense -> expense.getBetrag()).collect(Collectors.summingDouble(Double::doubleValue));

                if (value <= budget.budget) {
                    monateEingehaltenTotal++;
                }
            }
            badgesDTOList.add(new BadgesDTO(budget.budgetId, budget.kategorie, monateEingehaltenTotal));
        }

        return badgesDTOList;

    }

    @Override
    public BudgetStreakDTO findBudgetStreak(String userId) {

        LocalDateTime localDateTime = LocalDateTime.now();

        List<Expense> expenseList = expenseRepository.findByUserId("1");
        List<Budget> budgetList = budgetRepository.findByUserId("1");

        double monateBudgetStreak = 0;

        for(int monthOffset = 0; monthOffset < 24; monthOffset++) {

            final LocalDateTime adjustedDateTime = localDateTime.minusMonths(monthOffset);

            Boolean budgetMonthEingehaltenFlag = true;

            for(Budget budget : budgetList) {

                List<Expense> adjustedMonthYearList = expenseList.stream().filter(expense -> expense.datum.getYear() == adjustedDateTime.getYear() && expense.datum.getMonth() == adjustedDateTime.getMonth()).collect(Collectors.toList());

                List<Expense> ongoingMonthYearFilteredByKategorieList = adjustedMonthYearList.stream().filter(expense -> expense.kategorie.equals(budget.kategorie)).collect(Collectors.toList());
                double value = ongoingMonthYearFilteredByKategorieList.stream().map(expense -> expense.getBetrag()).collect(Collectors.summingDouble(Double::doubleValue));

                if (value > budget.budget) {
                    budgetMonthEingehaltenFlag = false;
                }
            }

            if(budgetMonthEingehaltenFlag) {
                monateBudgetStreak++;
            } else {
                break;
            }
        }

        return new BudgetStreakDTO(monateBudgetStreak);

    }
}
