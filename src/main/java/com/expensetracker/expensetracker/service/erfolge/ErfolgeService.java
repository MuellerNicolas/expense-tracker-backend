package com.expensetracker.expensetracker.service.erfolge;

import com.expensetracker.expensetracker.model.Budget;
import com.expensetracker.expensetracker.model.Expense;
import com.expensetracker.expensetracker.model.User;
import com.expensetracker.expensetracker.model.dto.BadgesDTO;
import com.expensetracker.expensetracker.model.dto.BudgetStreakDTO;
import com.expensetracker.expensetracker.repository.BudgetRepository;
import com.expensetracker.expensetracker.repository.ExpenseRepository;
import com.expensetracker.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for Erfolge-Tab in Expense-Tracker
 */
@Service
public class ErfolgeService implements ErfolgeServiceInterface {

    // Wire repositories
    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    BudgetRepository budgetRepository;
    @Autowired
    UserRepository userRepository;


    /**
     * Calculate badges per kategorie (Total of months within budget)
     * @param userId UserId
     * @return List of Badges to display
     */
    @Override
    public List<BadgesDTO> findBadges(String userId) {

        // Initialize Target BadgesList
        List<BadgesDTO> badgesDTOList = new ArrayList<>();

        // Get Local DateTime as starting point for Month Offset Calculation
        LocalDateTime localDateTime = LocalDateTime.now();

        // Buffer Expenses, Budgets and User
        List<Expense> expenseList = expenseRepository.findByUserId(userId);
        List<Budget> budgetList = budgetRepository.findByUserId(userId);
        User user = userRepository.findByUserId(userId);

        // Calculate Badges for every kategorie of expenses
        for(Budget budget : budgetList) {

            double monateEingehaltenTotal = 0;

            // Calculate months where expenses for kategorie have been in budget. Restriction of the calculation is the creation of the User-Account
            for(int monthOffset = 0; localDateTime.minusMonths(monthOffset).isAfter(user.getDatumCreated()) || localDateTime.minusMonths(monthOffset).isEqual(user.getDatumCreated()); monthOffset++) {

                double valueOfExpenses = ErfolgeService.getValueExpensesWithMonthOffset(monthOffset, localDateTime, expenseList, budget);

                if (valueOfExpenses <= budget.budget) {
                    monateEingehaltenTotal++;
                }
            }
            // Add Badge for kategorie to BadgesList
            badgesDTOList.add(new BadgesDTO(budget.budgetId, budget.kategorie, monateEingehaltenTotal));
        }

        return badgesDTOList;

    }


    /**
     * Calculate Streak of months where all kategories have been within budget
     * @param userId UserId
     * @return Streak of months where all kategories have been within budget to display
     */
    @Override
    public BudgetStreakDTO findBudgetStreak(String userId) {

        // Get Local DateTime as starting point for Month Offset Calculation
        LocalDateTime localDateTime = LocalDateTime.now();

        // Buffer Expenses, Budgets and User
        List<Expense> expenseList = expenseRepository.findByUserId(userId);
        List<Budget> budgetList = budgetRepository.findByUserId(userId);
        User user = userRepository.findByUserId(userId);

        // Streak of months where all kategories have been within budget
        double monateBudgetStreak = 0;

        // Calculate months where expenses for kategorie have been in budget. Begin with ongoing month, check earlier months
        for(int monthOffset = 0; localDateTime.minusMonths(monthOffset).isAfter(user.getDatumCreated()); monthOffset++) {

            Boolean budgetMonthEingehaltenFlag = true;

            // Calculate Budget for every kategorie of expenses in specified month. Set Flag if expenses were not in budget.
            for(Budget budget : budgetList) {

                double valueOfExpenses = ErfolgeService.getValueExpensesWithMonthOffset(monthOffset, localDateTime, expenseList, budget);

                if (valueOfExpenses > budget.budget) {
                    budgetMonthEingehaltenFlag = false;
                }
            }

            // Add to Streak for months where expenses have been in budget. Stop when expenses were not in budget.
            if(budgetMonthEingehaltenFlag) {
                monateBudgetStreak++;
            } else {
                break;
            }
        }

        return new BudgetStreakDTO(monateBudgetStreak);

    }

    /**
     * Sum up the value of the expenses in for specified kategorie and specified month.
     * @param monthOffset Offset, specifing target month for sum-operation
     * @param localDateTime Point of time, to start target month calculation
     * @param expenseList List of Expenses from Database
     * @param budget Budget from Database
     * @return Value of Expenses in target month for kategorie
     */
    public static double getValueExpensesWithMonthOffset(int monthOffset, LocalDateTime localDateTime, List<Expense> expenseList, Budget budget) {

        // Calculate target month from monthOffset
        final LocalDateTime adjustedDateTime = localDateTime.minusMonths(monthOffset);

        // Filter list of expenses on target month
        List<Expense> adjustedMonthYearList = expenseList.stream().filter(expense -> expense.datum.getYear() == adjustedDateTime.getYear() && expense.datum.getMonth() == adjustedDateTime.getMonth()).collect(Collectors.toList());

        // Filter list of expenses on target expense kategorie
        List<Expense> ongoingMonthYearFilteredByKategorieList = adjustedMonthYearList.stream().filter(expense -> expense.kategorie.equals(budget.kategorie)).collect(Collectors.toList());

        // Sum up filtered list of expenses
        double valueOfExpenses = ongoingMonthYearFilteredByKategorieList.stream().map(expense -> expense.getBetrag()).collect(Collectors.summingDouble(Double::doubleValue));

        return valueOfExpenses;
    }
}
