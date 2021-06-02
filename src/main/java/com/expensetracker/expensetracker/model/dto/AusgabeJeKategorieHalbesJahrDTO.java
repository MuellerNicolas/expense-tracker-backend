package com.expensetracker.expensetracker.model.dto;

import java.util.List;

public class AusgabeJeKategorieHalbesJahrDTO {

    public String name;
    public List<AusgabeJeKategorieAktuellerMonatDTO> series;

    public AusgabeJeKategorieHalbesJahrDTO(String name, List<AusgabeJeKategorieAktuellerMonatDTO> series) {
        this.name = name;
        this.series = series;
    }
}
