package com.expensetracker.expensetracker;

import com.expensetracker.expensetracker.controller.ErfolgeRestController;
import com.expensetracker.expensetracker.model.Expense;
import com.expensetracker.expensetracker.model.dto.BadgesDTO;
import com.expensetracker.expensetracker.model.dto.BudgetStreakDTO;
import com.expensetracker.expensetracker.repository.BudgetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)

public class ErfolgeIntegrationTests {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ErfolgeRestController erfolgeRestController;

    @Autowired
    private BudgetRepository budgetRepository;

    @Test
    public void accessApplication() {
        System.out.println(port);
    }

    @Test
    public void contextLoads() {
        assertThat(erfolgeRestController).isNotNull();
    }

    @Test
    public void getStreak() {

        // given

        // when
        ResponseEntity<BudgetStreakDTO> getResponse = this.testRestTemplate.getForEntity("http://localhost:" + port + "/api/budgetStreak", BudgetStreakDTO.class);

        // then
        BudgetStreakDTO retrievedStreakDTO = getResponse.getBody();

        assertThat(getResponse.getStatusCode().is2xxSuccessful());
        assertThat(getResponse.getBody().getMonateBudgetStreak()).isEqualTo(1d);

    }

    @Test
    public void getBadges() {

        // given
        int amountOfExpextedBadges = budgetRepository.findAll().size();

        // when
        ResponseEntity<BadgesDTO[]> getResponse = this.testRestTemplate.getForEntity("http://localhost:" + port + "/api/badges", BadgesDTO[].class);

        // then
        BadgesDTO[] retrievedBadgesDTO = getResponse.getBody();

        assertThat(retrievedBadgesDTO).isNotEmpty();
        assertThat(retrievedBadgesDTO.length).isEqualTo(amountOfExpextedBadges);

    }

}
