package com.expensetracker.expensetracker.service.erfolge;

import com.expensetracker.expensetracker.model.dto.BadgesDTO;
import com.expensetracker.expensetracker.model.dto.BudgetStreakDTO;

import java.util.List;

public interface ErfolgeServiceInterface {

    List<BadgesDTO> findBadges(String userId);

    BudgetStreakDTO findBudgetStreak(String userId);

}
