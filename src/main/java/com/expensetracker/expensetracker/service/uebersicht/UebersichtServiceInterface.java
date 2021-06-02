package com.expensetracker.expensetracker.service.uebersicht;

import com.expensetracker.expensetracker.model.Expense;
import com.expensetracker.expensetracker.model.dto.AusgabeJeKategorieAktuellerMonatDTO;
import com.expensetracker.expensetracker.model.dto.AusgabeJeKategorieHalbesJahrDTO;

import java.util.List;

public interface UebersichtServiceInterface {

    List<AusgabeJeKategorieAktuellerMonatDTO> findAusgabeJeKategorieAktuellerMonat(String userId);

    List<AusgabeJeKategorieHalbesJahrDTO> findAusgabeJeKategorieHalbesJahr(String userId);

    List<AusgabeJeKategorieAktuellerMonatDTO> findBudgetauslastungJeKategorieAktuellerMonat(String userId);
}
