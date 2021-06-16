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
        Budget ausbildungUndStudium = new Budget("Ausbildung und Studium", 2000d, "1");
        budgetRepository.save(ausbildungUndStudium);
        Budget reisen = new Budget("Reisen", 2000d, "1");
        budgetRepository.save(reisen);
        Budget infrastruktur = new Budget("Infrastruktur", 2000d, "1");
        budgetRepository.save(infrastruktur);
        Budget mobilität = new Budget("Mobilität", 2000d, "1");
        budgetRepository.save(mobilität);
        Budget bekleidung = new Budget("Bekleidung", 2000d, "1");
        budgetRepository.save(bekleidung);
        Budget freizeit = new Budget("Freizeit", 2000d, "1");
        budgetRepository.save(freizeit);
        Budget versicherung = new Budget("Versicherung", 2000d, "1");
        budgetRepository.save(versicherung);
        Budget sonstigeAusgaben = new Budget("Sonstige Ausgaben", 2000d, "1");
        budgetRepository.save(sonstigeAusgaben);
        Budget essenUndTrinken = new Budget("Essen und Trinken", 2000d, "1");
        budgetRepository.save(essenUndTrinken);
    }

    @ChangeSet(order= "002", id = "seedUser", author = "InitialConfig")
    public void seedUser(UserRepository userRepository) {
        User standaloneUser = new User("1", "StandaloneUser", "****", "test@email.de", LocalDateTime.now().minusMonths(6));
        userRepository.save(standaloneUser);
    }

}
