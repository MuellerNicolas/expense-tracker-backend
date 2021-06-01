package com.expensetracker.expensetracker.service.uebersicht;


import com.expensetracker.expensetracker.model.Expense;
import com.expensetracker.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UebersichtService implements UebersichtServiceInterface {

    @Autowired
    ExpenseRepository expenseRepository;

    @Override
    public List<Expense> findAusgabeJeKategorieAktuellerMonat(String userId) {
        return null;
    }

}
