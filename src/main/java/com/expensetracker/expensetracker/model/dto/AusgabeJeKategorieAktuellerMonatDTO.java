package com.expensetracker.expensetracker.model.dto;

/**
 * DataTypeObject for displaying expenses per kategorie in the ongoing month
 */
public class AusgabeJeKategorieAktuellerMonatDTO {

    public String name;
    public double value;

    public AusgabeJeKategorieAktuellerMonatDTO(String name, double value) {
        this.name = name;
        this.value = value;
    }
}
