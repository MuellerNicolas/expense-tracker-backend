package com.expensetracker.expensetracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class Kategorie {

    @Id
    String kategorieID;
    String kategorieName;
    Double budget;

    public Kategorie(String kategorieId, String kategorieName, Double budget) {
        this.kategorieID = kategorieId;
        this.kategorieName = kategorieName;
        this.budget = budget;
    }


}
