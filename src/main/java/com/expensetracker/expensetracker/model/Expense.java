package com.expensetracker.expensetracker.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Model for Expense in Expense-Tracker
 */
@Document(collection = "expenses")
public class Expense {

    @Id
    public String expenseId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime datum;
    public String name;
    public Double betrag;
    public String kategorie;
    public String userId;

    public Expense(LocalDateTime datum, String name, Double betrag, String kategorie, String userId) {
        this.datum = datum;
        this.name = name;
        this.betrag = betrag;
        this.kategorie = kategorie;
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

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Objects.equals(expenseId, expense.expenseId) && Objects.equals(datum, expense.datum) && Objects.equals(name, expense.name) && Objects.equals(betrag, expense.betrag) && Objects.equals(kategorie, expense.kategorie) && Objects.equals(userId, expense.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expenseId, datum, name, betrag, kategorie, userId);
    }
}

