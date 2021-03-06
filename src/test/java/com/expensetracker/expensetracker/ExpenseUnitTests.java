package com.expensetracker.expensetracker;

import com.expensetracker.expensetracker.model.Expense;
import com.expensetracker.expensetracker.repository.ExpenseRepository;
import com.expensetracker.expensetracker.service.expense.ExpenseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit Tests for ExpenseTrackerBackend
 *
 * Tests are decoupled from concrete MongoDB Server
 */

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class ExpenseUnitTests {

    @Autowired
    private ExpenseRepository expenseRepository;

    private final String userId = "1";

    private ExpenseService cut;

    private Expense expenseToSave;

    @BeforeEach
    public void dataSetup() {

        expenseToSave = new Expense(LocalDateTime.now(), "Fahrrad", 1600d, "1", userId );

        this.cut = new ExpenseService(expenseRepository);

    }

    @AfterEach
    public void dataCleanUp() {
        expenseRepository.deleteAll();
    }

    @Test
    public void AddExpense() {

        // given
        cut.saveOrUpdateExpense(expenseToSave);

        // when
        Expense retrievedExpense = cut.findExpenseById(expenseToSave.expenseId);

        // then
        assertThat(retrievedExpense.equals(expenseToSave));


    }

    @Test
    public void UpdateExpense() {

        // given
        expenseToSave.setBetrag(1800d);
        cut.saveOrUpdateExpense(expenseToSave);

        // when
        Expense retrievedExpense = cut.findExpenseById(expenseToSave.expenseId);

        // then
        assertThat(retrievedExpense.getBetrag()).isEqualTo(expenseToSave.getBetrag());

    }

    @Test
    public void DeleteExpense() {

        // given
        cut.saveOrUpdateExpense(expenseToSave);

        // when
        cut.deleteExpenseById(expenseToSave.expenseId);
        Expense retrievedExpense = cut.findExpenseById(expenseToSave.expenseId);

        // then
        assertThat(retrievedExpense).isNull();

    }

    @Test
    public void GetLastEntries() {

        // given
        List<Expense> expenseToSaveList = new ArrayList<>();
        for(double x = 0; x < 12; x++){
            expenseToSave = new Expense(LocalDateTime.now(), "Fahrrad", x, "1", userId );
            expenseToSaveList.add(expenseToSave);
            cut.saveOrUpdateExpense(expenseToSave);
        }

        // when
        List<Expense> retrievedExpenseList = cut.findLatestExpenses(userId);

        // then
        // Assert that retrieved List is not empty
        assertThat(retrievedExpenseList).isNotEmpty();
        // Assert that retrieved amount of Expenses equals expected amount of Expenses
        assertThat(expenseToSaveList.size()).isEqualTo(retrievedExpenseList.size());
        // Assert that Expenses are ordered by Date descending
        for(int index = 0; index < retrievedExpenseList.size()-1; index++) {
            retrievedExpenseList.get(index).getDatum().isBefore(retrievedExpenseList.get(index+1).getDatum());
        }

    }



}
