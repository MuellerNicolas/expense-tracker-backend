package com.expensetracker.expensetracker;

import com.expensetracker.expensetracker.controller.ExpenseRestController;
import com.expensetracker.expensetracker.model.Expense;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class ExpenseIntegrationTests {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ExpenseRestController expenseRestController;

    private Expense expenseToSave;
    private Expense retrievedExpense;
    private final String userId = "1";

    @Test
    public void accessApplication() {
        System.out.println(port);
    }

    @Test
    public void contextLoads() {
        assertThat(expenseRestController).isNotNull();
    }

    @Test
    public void postExpense() {

        // given
        expenseToSave = new Expense(LocalDateTime.now(), "Fahrrad", 1600d, "1", userId );

        // when
        ResponseEntity<Expense> response = this.testRestTemplate.postForEntity("http://localhost:" + port + "/api/ausgaben/", expenseToSave, Expense.class);

        // then
        retrievedExpense  = response.getBody();

        assertThat(response.getStatusCode().is2xxSuccessful());
        assertThat(response.getBody().equals(expenseToSave));

    }

    @Test
    public void getExpenseById() {

        // given
        expenseToSave = new Expense(LocalDateTime.now(), "Fahrrad", 1600d, "1", userId );
        ResponseEntity<Expense> postResponse = this.testRestTemplate.postForEntity("http://localhost:" + port + "/api/ausgaben/", expenseToSave, Expense.class);
        retrievedExpense  = postResponse.getBody();

        // when
        ResponseEntity<Expense> getResponse = this.testRestTemplate.getForEntity("http://localhost:" + port + "/api/ausgaben/{id}", Expense.class, retrievedExpense.getExpenseId());

        // then
        Expense retrievedGetResponse  = getResponse.getBody();

        assertThat(postResponse.getStatusCode().is2xxSuccessful());
        assertThat(retrievedGetResponse.equals(expenseToSave));

    }

    @Test
    public void putExpenseById() {

        // given
        expenseToSave = new Expense(LocalDateTime.now(), "Fahrrad", 1600d, "1", userId );
        ResponseEntity<Expense> postResponse = this.testRestTemplate.postForEntity("http://localhost:" + port + "/api/ausgaben/", expenseToSave, Expense.class);
        retrievedExpense  = postResponse.getBody();

        // when
        ResponseEntity<Expense> getResponse = this.testRestTemplate.getForEntity("http://localhost:" + port + "/api/ausgaben/{id}", Expense.class, retrievedExpense.getExpenseId());
        Expense retrievedGetResponse  = getResponse.getBody();
        retrievedGetResponse.setBetrag(1800d);
        this.testRestTemplate.put("http://localhost:" + port + "/api/ausgaben/{id}", retrievedGetResponse,  retrievedExpense.getExpenseId());

        ResponseEntity<Expense> checkSameExpenseAgain = this.testRestTemplate.getForEntity("http://localhost:" + port + "/api/ausgaben/{id}", Expense.class, retrievedExpense.getExpenseId());
        Expense changedExpense  = checkSameExpenseAgain.getBody();

        // then
        assertThat(!changedExpense.equals(expenseToSave));
        assertThat(changedExpense.getBetrag()).isEqualTo(1800d);

    }

    @Test
    public void getExpenses() {

        // given
        expenseToSave = new Expense(LocalDateTime.now(), "Fahrrad", 1600d, "1", userId );
        ResponseEntity<Expense> postResponse = this.testRestTemplate.postForEntity("http://localhost:" + port + "/api/ausgaben/", expenseToSave, Expense.class);

        // when
        ResponseEntity<Expense[]> expenseGetAllResponse = this.testRestTemplate.getForEntity("http://localhost:" + port + "/api/ausgaben/", Expense[].class);
        Expense[] expense = expenseGetAllResponse.getBody();

        // then
        assertThat(expense).isNotEmpty();
        assertThat(expense).isNotNull();

    }

}
