package com.expensetracker.expensetracker.service.uebersicht;


import com.expensetracker.expensetracker.model.Budget;
import com.expensetracker.expensetracker.model.Expense;
import com.expensetracker.expensetracker.model.dto.AusgabeJeKategorieAktuellerMonatDTO;
import com.expensetracker.expensetracker.model.dto.AusgabeJeKategorieHalbesJahrDTO;
import com.expensetracker.expensetracker.repository.BudgetRepository;
import com.expensetracker.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UebersichtService implements UebersichtServiceInterface {

    @Autowired
    ExpenseRepository expenseRepository;

    @Autowired
    BudgetRepository budgetRepository;

    @Override
    public List<AusgabeJeKategorieAktuellerMonatDTO> findAusgabeJeKategorieAktuellerMonat(String userId) {

        List<AusgabeJeKategorieAktuellerMonatDTO> ausgabeJeKategorieAktuellerMonatDTOList = new ArrayList<>();
        int ongoingYear = LocalDateTime.now().getYear();
        Month ongoingMonth = LocalDateTime.now().getMonth();

        List<Expense> expenseList = expenseRepository.findByUserId("1");
        List<Budget> budgetList = budgetRepository.findByUserId("1");

        List<Expense> ongoingMonthYearList = expenseList.stream().filter(expense -> expense.datum.getYear() == ongoingYear && expense.datum.getMonth() == ongoingMonth).collect(Collectors.toList());

        for(Budget budget : budgetList) {

            List<Expense> ongoingMonthYearFilteredByKategorieList = ongoingMonthYearList.stream().filter(expense -> expense.kategorie.equals(budget.kategorie)).collect(Collectors.toList());
            double value = ongoingMonthYearFilteredByKategorieList.stream().map(expense -> expense.getBetrag()).collect(Collectors.summingDouble(Double::doubleValue));

            ausgabeJeKategorieAktuellerMonatDTOList.add(new AusgabeJeKategorieAktuellerMonatDTO(budget.kategorie, value));
        }

        return ausgabeJeKategorieAktuellerMonatDTOList;
    }

    @Override
    public List<AusgabeJeKategorieHalbesJahrDTO> findAusgabeJeKategorieHalbesJahr(String userId) {
        List<AusgabeJeKategorieHalbesJahrDTO> ausgabeJeKategorieHalbesJahrDTOList = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();

        List<Expense> expenseList = expenseRepository.findByUserId("1");
        List<Budget> budgetList = budgetRepository.findByUserId("1");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM");


        for(Budget budget : budgetList) {

            List<AusgabeJeKategorieAktuellerMonatDTO> ausgabeJeKategorieMonatList = new ArrayList<>();

            for(int monthOffset = 0; monthOffset < 6; monthOffset++) {

                final LocalDateTime adjustedDateTime = localDateTime.minusMonths(monthOffset);

                List<Expense> adjustedMonthYearList = expenseList.stream().filter(expense -> expense.datum.getYear() == adjustedDateTime.getYear() && expense.datum.getMonth() == adjustedDateTime.getMonth()).collect(Collectors.toList());

                List<Expense> ongoingMonthYearFilteredByKategorieList = adjustedMonthYearList.stream().filter(expense -> expense.kategorie.equals(budget.kategorie)).collect(Collectors.toList());
                double value = ongoingMonthYearFilteredByKategorieList.stream().map(expense -> expense.getBetrag()).collect(Collectors.summingDouble(Double::doubleValue));

                ausgabeJeKategorieMonatList.add(new AusgabeJeKategorieAktuellerMonatDTO(adjustedDateTime.format(dateFormatter), value));


            }

            ausgabeJeKategorieHalbesJahrDTOList.add(new AusgabeJeKategorieHalbesJahrDTO(budget.kategorie, ausgabeJeKategorieMonatList));

        }

        return ausgabeJeKategorieHalbesJahrDTOList;
    }

    @Override
    public List<AusgabeJeKategorieAktuellerMonatDTO> findBudgetauslastungJeKategorieAktuellerMonat(String userId) {

        List<AusgabeJeKategorieAktuellerMonatDTO> budgetauslastungJeKategorieAktuellerMonatList = new ArrayList<>();

        int ongoingYear = LocalDateTime.now().getYear();
        Month ongoingMonth = LocalDateTime.now().getMonth();

        List<Expense> expenseList = expenseRepository.findByUserId("1");
        List<Budget> budgetList = budgetRepository.findByUserId("1");

        List<Expense> ongoingMonthYearList = expenseList.stream().filter(expense -> expense.datum.getYear() == ongoingYear && expense.datum.getMonth() == ongoingMonth).collect(Collectors.toList());

        for(Budget budget : budgetList) {

            List<Expense> ongoingMonthYearFilteredByKategorieList = ongoingMonthYearList.stream().filter(expense -> expense.kategorie.equals(budget.kategorie)).collect(Collectors.toList());
            double value = ongoingMonthYearFilteredByKategorieList.stream().map(expense -> expense.getBetrag()).collect(Collectors.summingDouble(Double::doubleValue));

            Double percentage = 0d;

            if (budget.budget != 0) {
                percentage = value / budget.budget * 100;
            } else {
                if(value == 0) {
                    percentage = 100d;
                } else {
                    percentage = 110d;
                }
            }

            
            budgetauslastungJeKategorieAktuellerMonatList.add(new AusgabeJeKategorieAktuellerMonatDTO(budget.kategorie, percentage));
        }

        return budgetauslastungJeKategorieAktuellerMonatList;
    }


}
