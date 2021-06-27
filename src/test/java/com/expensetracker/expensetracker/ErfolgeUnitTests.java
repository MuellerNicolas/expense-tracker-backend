package com.expensetracker.expensetracker;

import com.expensetracker.expensetracker.model.Expense;
import com.expensetracker.expensetracker.model.User;
import com.expensetracker.expensetracker.model.dto.BadgesDTO;
import com.expensetracker.expensetracker.model.dto.BudgetStreakDTO;
import com.expensetracker.expensetracker.repository.BudgetRepository;
import com.expensetracker.expensetracker.repository.ExpenseRepository;
import com.expensetracker.expensetracker.repository.UserRepository;
import com.expensetracker.expensetracker.service.erfolge.ErfolgeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit Tests for ExpenseTrackerBackend
 *
 * Tests are decoupled from concrete MongoDB Server
 */

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class ErfolgeUnitTests {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private UserRepository userRepository;

    private final String userId = "1";

    private Expense expenseToSave;

    private ErfolgeService cut;

    @BeforeEach
    void setUp() {
        expenseRepository.deleteAll();
        this.cut = new ErfolgeService(expenseRepository, budgetRepository, userRepository);
        expenseToSave = new Expense(LocalDateTime.now(), "Fahrrad", 2600d, "Mobilität", userId );

    }

    @AfterEach
    public void dataCleanUp() {
        expenseRepository.deleteAll();
    }

    @Test
    public void StreakBudgetSeitErstellungEingehalten() {

        // given
        User user = userRepository.findByUserId(userId);

        // Get Local DateTime as starting point for Month Offset Calculation
        LocalDate firstDateOfOngoingMonth = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()).toLocalDate();
        LocalDate firstDateOfMonthUserCreated = user.getDatumCreated().with(TemporalAdjusters.firstDayOfMonth()).toLocalDate();

        long monateSeitAccountErstellung = 0;

        for(; firstDateOfOngoingMonth.minusMonths(monateSeitAccountErstellung).isAfter(firstDateOfMonthUserCreated) || firstDateOfOngoingMonth.minusMonths(monateSeitAccountErstellung).equals(firstDateOfMonthUserCreated); monateSeitAccountErstellung++){}

        // when
        BudgetStreakDTO streak = cut.findBudgetStreak(userId);

        // then
        assertThat(streak.getMonateBudgetStreak()).isNotNull();
        assertThat(streak.getMonateBudgetStreak()).isEqualTo(monateSeitAccountErstellung);

    }

    @Test
    public void StreakBudget1MonatEingehalten() {

        // given
        User user = userRepository.findByUserId(userId);

        // Get Local DateTime as starting point for Month Offset Calculation
        LocalDate firstDateOfOngoingMonth = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()).toLocalDate();
        LocalDate firstDateOfMonthUserCreated = user.getDatumCreated().with(TemporalAdjusters.firstDayOfMonth()).toLocalDate();

        expenseToSave = new Expense(LocalDateTime.now().minusMonths(1), "Fahrrad", 2600d, "Mobilität", userId );
        expenseRepository.save(expenseToSave);

        // when
        BudgetStreakDTO streak = cut.findBudgetStreak(userId);

        // then
        assertThat(streak.getMonateBudgetStreak()).isNotNull();
        assertThat(streak.getMonateBudgetStreak()).isEqualTo(1);

    }

    @Test
    public void StreakBudget0MonateEingehalten() {

        // given
        User user = userRepository.findByUserId(userId);

        // Get Local DateTime as starting point for Month Offset Calculation
        LocalDate firstDateOfOngoingMonth = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()).toLocalDate();
        LocalDate firstDateOfMonthUserCreated = user.getDatumCreated().with(TemporalAdjusters.firstDayOfMonth()).toLocalDate();

        expenseToSave = new Expense(LocalDateTime.now(), "Fahrrad", 2600d, "Mobilität", userId );
        expenseRepository.save(expenseToSave);

        // when
        BudgetStreakDTO streak = cut.findBudgetStreak(userId);

        // then
        assertThat(streak.getMonateBudgetStreak()).isNotNull();
        assertThat(streak.getMonateBudgetStreak()).isEqualTo(0);

    }

    @Test
    public void BadgesBudgetSeitErstellungEingehalten() {

        // given
        User user = userRepository.findByUserId(userId);

        // Get Local DateTime as starting point for Month Offset Calculation
        LocalDate firstDateOfOngoingMonth = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()).toLocalDate();
        LocalDate firstDateOfMonthUserCreated = user.getDatumCreated().with(TemporalAdjusters.firstDayOfMonth()).toLocalDate();

        long monateSeitAccountErstellung = 0;

        for(; firstDateOfOngoingMonth.minusMonths(monateSeitAccountErstellung).isAfter(firstDateOfMonthUserCreated) || firstDateOfOngoingMonth.minusMonths(monateSeitAccountErstellung).equals(firstDateOfMonthUserCreated); monateSeitAccountErstellung++){}

        // when
        List<BadgesDTO> badgesDTOList = cut.findBadges(userId);

        // then
        assertThat(badgesDTOList).isNotNull();

        Double monateeingehalten = badgesDTOList.stream().filter(badge -> badge.kategorie.equals("Mobilität")).findFirst().orElse(null).getMonateEingehaltenTotal();

        assertThat(monateeingehalten).isEqualTo((double) monateSeitAccountErstellung);


    }

    @Test
    public void BadgesBudget1MonatNichtEingehalten() {

        // given
        User user = userRepository.findByUserId(userId);

        // Get Local DateTime as starting point for Month Offset Calculation
        LocalDate firstDateOfOngoingMonth = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()).toLocalDate();
        LocalDate firstDateOfMonthUserCreated = user.getDatumCreated().with(TemporalAdjusters.firstDayOfMonth()).toLocalDate();

        long monateSeitAccountErstellung = 0;

        for(; firstDateOfOngoingMonth.minusMonths(monateSeitAccountErstellung).isAfter(firstDateOfMonthUserCreated) || firstDateOfOngoingMonth.minusMonths(monateSeitAccountErstellung).equals(firstDateOfMonthUserCreated); monateSeitAccountErstellung++){

        }

        expenseToSave = new Expense(firstDateOfOngoingMonth.atStartOfDay(), "Fahrrad", 2600d, "Mobilität", userId );
        expenseRepository.save(expenseToSave);

        // when
        List<BadgesDTO> badgesDTOList = cut.findBadges(userId);

        // then
        assertThat(badgesDTOList).isNotNull();

        Double monateeingehalten = badgesDTOList.stream().filter(badge -> badge.kategorie.equals("Mobilität")).findFirst().orElse(null).getMonateEingehaltenTotal();

        assertThat(monateeingehalten).isEqualTo((double) monateSeitAccountErstellung-1);

    }

    @Test
    public void BadgesBudgetJedenMonatNichtEingehalten() {

        // given
        User user = userRepository.findByUserId(userId);

        // Get Local DateTime as starting point for Month Offset Calculation
        LocalDate firstDateOfOngoingMonth = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()).toLocalDate();
        LocalDate firstDateOfMonthUserCreated = user.getDatumCreated().with(TemporalAdjusters.firstDayOfMonth()).toLocalDate();

        long monateSeitAccountErstellung = 0;

        for(; firstDateOfOngoingMonth.minusMonths(monateSeitAccountErstellung).isAfter(firstDateOfMonthUserCreated) || firstDateOfOngoingMonth.minusMonths(monateSeitAccountErstellung).equals(firstDateOfMonthUserCreated); monateSeitAccountErstellung++){
            expenseToSave = new Expense(firstDateOfOngoingMonth.minusMonths(monateSeitAccountErstellung).atStartOfDay(), "Fahrrad", 2600d, "Mobilität", userId );
            expenseRepository.save(expenseToSave);
        }

        // when
        List<BadgesDTO> badgesDTOList = cut.findBadges(userId);

        // then
        assertThat(badgesDTOList).isNotNull();

        Double monateeingehalten = badgesDTOList.stream().filter(badge -> badge.kategorie.equals("Mobilität")).findFirst().orElse(null).getMonateEingehaltenTotal();

        assertThat(monateeingehalten.equals(0));
        assertThat(monateeingehalten).isEqualTo(0d);


    }

}
