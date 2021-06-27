package com.expensetracker.expensetracker.initialdbconfig;

import com.expensetracker.expensetracker.model.Budget;
import com.expensetracker.expensetracker.model.Expense;
import com.expensetracker.expensetracker.model.User;
import com.expensetracker.expensetracker.repository.BudgetRepository;
import com.expensetracker.expensetracker.repository.ExpenseRepository;
import com.expensetracker.expensetracker.repository.UserRepository;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;

import java.time.LocalDateTime;


@ChangeLog
public class DBConfig {

    @ChangeSet(order= "001", id = "seedBudget", author = "InitialConfig")
    public void seedBudget(BudgetRepository budgetRepository){
        Budget ausbildungUndStudium = new Budget("Ausbildung und Studium", 100d, "1");
        budgetRepository.save(ausbildungUndStudium);
        Budget reisen = new Budget("Reisen", 300d, "1");
        budgetRepository.save(reisen);
        Budget infrastruktur = new Budget("Infrastruktur", 200d, "1");
        budgetRepository.save(infrastruktur);
        Budget mobilität = new Budget("Mobilität", 200d, "1");
        budgetRepository.save(mobilität);
        Budget bekleidung = new Budget("Bekleidung", 100d, "1");
        budgetRepository.save(bekleidung);
        Budget freizeit = new Budget("Freizeit", 100d, "1");
        budgetRepository.save(freizeit);
        Budget versicherung = new Budget("Versicherung", 200d, "1");
        budgetRepository.save(versicherung);
        Budget sonstigeAusgaben = new Budget("Sonstige Ausgaben", 100d, "1");
        budgetRepository.save(sonstigeAusgaben);
        Budget essenUndTrinken = new Budget("Essen und Trinken", 300d, "1");
        budgetRepository.save(essenUndTrinken);
    }

    @ChangeSet(order= "002", id = "seedUser", author = "InitialConfig")
    public void seedUser(UserRepository userRepository) {
        User standaloneUser = new User("1", "StandaloneUser", "****", "test@email.de", LocalDateTime.now().minusMonths(6));
        userRepository.save(standaloneUser);
    }

    @ChangeSet(order= "003", id = "seedExpenses", author = "InitialConfig")
    public void seedExpenses(ExpenseRepository expenseRepository) {
        // Ausbildung und Studium
        Expense expense1 = new Expense(LocalDateTime.now().minusMonths(5), "Semestergebühren", 60d, "Ausbildung und Studium", "1");
        expenseRepository.save(expense1);
        Expense expense2 = new Expense(LocalDateTime.now().minusMonths(4), "Semestergebühren", 60d, "Ausbildung und Studium", "1");
        expenseRepository.save(expense2);
        Expense expense3 = new Expense(LocalDateTime.now().minusMonths(3), "Semestergebühren", 60d, "Ausbildung und Studium", "1");
        expenseRepository.save(expense3);
        Expense expense4 = new Expense(LocalDateTime.now().minusMonths(2), "Semestergebühren", 60d, "Ausbildung und Studium", "1");
        expenseRepository.save(expense4);
        Expense expense5 = new Expense(LocalDateTime.now().minusMonths(1), "Semestergebühren", 60d, "Ausbildung und Studium", "1");
        expenseRepository.save(expense5);
        Expense expense6 = new Expense(LocalDateTime.now().minusMonths(1), "Bücher", 70d, "Ausbildung und Studium", "1");
        expenseRepository.save(expense6);
        Expense expense15 = new Expense(LocalDateTime.now(), "Semestergebühren", 60d, "Ausbildung und Studium", "1");
        expenseRepository.save(expense15);

        // Freizeit
        Expense expense7 = new Expense(LocalDateTime.now().minusMonths(3), "Europapark", 120d, "Freizeit", "1");
        expenseRepository.save(expense7);
        Expense expense8 = new Expense(LocalDateTime.now().minusMonths(2), "Ago", 140d, "Freizeit", "1");
        expenseRepository.save(expense8);

        // Bekleidung
        Expense expense9 = new Expense(LocalDateTime.now().minusMonths(2), "Anzug", 200d, "Bekleidung", "1");
        expenseRepository.save(expense9);
        Expense expense10 = new Expense(LocalDateTime.now().minusMonths(4), "Schuhe", 80d, "Bekleidung", "1");
        expenseRepository.save(expense10);
        Expense expense11 = new Expense(LocalDateTime.now(), "Schuhe", 80d, "Bekleidung", "1");
        expenseRepository.save(expense11);

        // Infrastruktur
        Expense expense12 = new Expense(LocalDateTime.now().minusMonths(1), "Handyvertrag", 15d, "Infrastruktur", "1");
        expenseRepository.save(expense12);
        Expense expense13 = new Expense(LocalDateTime.now(), "Handyvertrag", 15d, "Infrastruktur", "1");
        expenseRepository.save(expense13);
        Expense expense14 = new Expense(LocalDateTime.now(), "Handyvertrag", 15d, "Infrastruktur", "1");
        expenseRepository.save(expense14);

    }

}
