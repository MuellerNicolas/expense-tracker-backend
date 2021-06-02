package com.expensetracker.expensetracker.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "expenses")
public class Expense {

    @Id
    public String expenseId;
    public LocalDateTime datum;
    public String name;
    public Double betrag;
    public String kategorieId;
    public String userId;

    public Expense(LocalDateTime datum, String name, Double betrag, String kategorieId, String userId) {
        this.datum = datum;
        this.name = name;
        this.betrag = betrag;
        this.kategorieId = kategorieId;
        this.userId = userId;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBetrag() {
        return betrag;
    }

    public void setBetrag(Double betrag) {
        this.betrag = betrag;
    }

    public String getKategorieId() {
        return kategorieId;
    }

    public void setKategorieId(String kategorieId) {
        this.kategorieId = kategorieId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

