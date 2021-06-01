package com.expensetracker.expensetracker.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "expenses")
public class Expense {

    @Id
    public String expenseId;
    public Date datum;
    public String name;
    public Double betrag;
    public String kategorieId;
    public String userId;

    public Expense(Date datum, String name, Double betrag, String kategorieId, String userId) {
               this.datum = datum;
        this.name = name;
        this.betrag = betrag;
        this.kategorieId = kategorieId;
        this.userId = userId;
    }
}

