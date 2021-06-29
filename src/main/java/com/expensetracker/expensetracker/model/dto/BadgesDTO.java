package com.expensetracker.expensetracker.model.dto;

import java.util.Objects;

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

    public BadgesDTO() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BadgesDTO badgesDTO = (BadgesDTO) o;
        return Objects.equals(id, badgesDTO.id) && Objects.equals(kategorie, badgesDTO.kategorie) && Objects.equals(monateEingehaltenTotal, badgesDTO.monateEingehaltenTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, kategorie, monateEingehaltenTotal);
    }
}
