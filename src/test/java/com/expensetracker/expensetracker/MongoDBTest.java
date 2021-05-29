package com.expensetracker.expensetracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Unit Tests for ExpenseTrackerBackend
 *
 * Tests are decoupled from concrete MongoDB Server
 */

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class MongoDBTest {

    private Expense expenseToSave;

    @Autowired
    private ExpenseRepository expenseRepository;

    @BeforeEach
    public void dataSetup() {

        expenseToSave = new Expense(new Date(), "Fahrrad", 1600d, "1", "1" );

    }

    @Test
    public void AddExpense() {

        expenseRepository.save(expenseToSave);

        Optional<Expense> retrievedExpense = expenseRepository.findById(expenseToSave.id);

        System.out.println(retrievedExpense.get().id);


        assertThat(retrievedExpense).isNotEmpty();
        assertThat(retrievedExpense.get().id).isEqualTo(expenseToSave.id);

    }

    @Test
    public void UpdateExpense() {

    }

    @Test
    public void DeleteExpense() {

    }

    @Test
    public void Get10LastEntries() {
        for(double x = 0; x < 12; x++){
            expenseToSave = new Expense(new Date(), "Fahrrad", x, "1", "1" );
            expenseRepository.save(expenseToSave);

        }
        String datum = "datum";
        List<Expense> expenseList = expenseRepository.findTop10ByOrderByDatumDesc();

        assertThat(expenseList).isNotEmpty();

        expenseList.forEach(expense -> System.out.println(expense.betrag));




    }




}