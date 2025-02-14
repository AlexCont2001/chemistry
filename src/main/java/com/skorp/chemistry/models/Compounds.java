package com.skorp.chemistry.models;

import jakarta.persistence.*;

@Entity
@Table(name = "compounds")
public class Compounds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String Name;
    private String IupacName;
    private String Formula;
    private Double MolarMass;
    private int Composition;
    private int ChemicalBonding;
    private int ChemicalProperty;

    public int getChemicalProperty() {
        return ChemicalProperty;
    }

    public void setChemicalProperty(int chemicalProperty) {
        ChemicalProperty = chemicalProperty;
    }

    public int getChemicalBonding() {
        return ChemicalBonding;
    }

    public void setChemicalBonding(int chemicalBonding) {
        ChemicalBonding = chemicalBonding;
    }

    public int getComposition() {
        return Composition;
    }

    public void setComposition(int composition) {
        Composition = composition;
    }

    public Double getMolarMass() {
        return MolarMass;
    }

    public void setMolarMass(Double molarMass) {
        MolarMass = molarMass;
    }

    public String getIupacName() {
        return IupacName;
    }

    public void setIupacName(String iupacName) {
        IupacName = iupacName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFormula() {
        return Formula;
    }

    public void setFormula(String formula) {
        Formula = formula;
    }
}
