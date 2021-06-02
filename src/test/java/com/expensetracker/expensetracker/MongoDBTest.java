package com.expensetracker.expensetracker;

import com.expensetracker.expensetracker.model.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Unit Tests for ExpenseTrackerBackend
 *
 * Tests are decoupled from concrete MongoDB Server
 */

public class MongoDBTest {

    private Expense expenseToSave;

    /*
    @Autowired
    private ExpenseRepository expenseRepository;
    */
    /*
    @Autowired
    private MongoTemplate mongoTemplate;
    */
    @BeforeEach
    public void dataSetup() {

        expenseToSave = new Expense(LocalDateTime.now(), "Fahrrad", 1600d, "1", "1" );

    }

    @Test
    public void AddExpense() {

        //mongoTemplate.save(expenseToSave);

        //Expense retrievedExpense = mongoTemplate.findById(expenseToSave.expenseId);

        //assertThat(retrievedExpense).isNotEmpty();
        //assertThat(retrievedExpense.get().id).isEqualTo(expenseToSave.expenseId);
        //assertThat(retrievedExpense.get().datum).isEqualTo(expenseToSave.datum);

    }

    @Test
    public void UpdateExpense() {

        /*expenseRepository.save(expenseToSave);

        Expense retrievedExpense = expenseRepository.findById();

        retrievedExpense.get().betrag = 100d;

        expenseRepository.save(retrievedExpense);

        Expense updatedExpense = expenseRepository.findById(expenseToSave.expenseId);


        assertThat(updatedExpense.isNotNull());
        assertThat(updatedExpense.get().datum).isEqualTo(expenseToSave.datum);
        assertThat(updatedExpense.get().betrag).isEqualTo(100d);*/

    }

    @Test
    public void DeleteExpense() {

    }

    @Test
    public void Get10LastEntries() {

        /*for(double x = 0; x < 12; x++){
            expenseToSave = new Expense(new Date(), "Fahrrad", x, "1", "1" );
            expenseRepository.save(expenseToSave);

        }
        String datum = "datum";
        List<Expense> expenseList = expenseRepository.findTop10ByUserIdByOrderByDatumDesc();

        assertThat(expenseList).isNotEmpty();

        expenseList.forEach(expense -> System.out.println(expense.betrag));*/




    }




}
