package com.expensetracker.expensetracker;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "expenses")
public class Expense {

    @Id
    public String expenseID;
    public Date datum;
    public String name;
    public Double betrag;
    public String kategorieID;
    public String userID;

    public Expense(Date datum, String name, Double betrag, String kategorieID, String userID) {
               this.datum = datum;
        this.name = name;
        this.betrag = betrag;
        this.kategorieID = kategorieID;
        this.userID = userID;
    }
}

