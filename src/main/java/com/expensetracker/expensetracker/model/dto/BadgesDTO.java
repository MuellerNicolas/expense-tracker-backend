package com.expensetracker.expensetracker.model.dto;

/**
 * DataTypeObject for displaying badges per kategorie. Badges displaying total of months within budget.
 */
public class BadgesDTO {

    public String id;
    public String kategorie;
    public Double monateEingehaltenTotal;

    public BadgesDTO(String id, String kategorie, Double monateEingehaltenTotal) {
        this.id = id;
        this.kategorie = kategorie;
        this.monateEingehaltenTotal = monateEingehaltenTotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public Double getMonateEingehaltenTotal() {
        return monateEingehaltenTotal;
    }

    public void setMonateEingehaltenTotal(Double monateEingehaltenTotal) {
        this.monateEingehaltenTotal = monateEingehaltenTotal;
    }
}
