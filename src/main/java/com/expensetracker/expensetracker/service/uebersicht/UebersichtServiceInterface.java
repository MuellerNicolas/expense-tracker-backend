package com.expensetracker.expensetracker.service.uebersicht;

import com.expensetracker.expensetracker.model.Expense;

import java.util.List;

public interface UebersichtServiceInterface {

    List<Expense> findAusgabeJeKategorieAktuellerMonat(String userId);

}
