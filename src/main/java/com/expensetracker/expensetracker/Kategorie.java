package com.expensetracker.expensetracker;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
public class Kategorie {

    @Id
    String kategorieID;
    String kategorieName;
    Double budget;

    public Kategorie(String kategorieID, String kategorieName, Double budget) {
        this.kategorieID = kategorieID;
        this.kategorieName = kategorieName;
        this.budget = budget;
    }


}
