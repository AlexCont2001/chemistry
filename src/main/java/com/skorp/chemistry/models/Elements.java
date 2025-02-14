package com.skorp.chemistry.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Reference;

import java.math.BigDecimal;

@Entity
@Table(name = "elements")
public class Elements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String Name;

    private int AtomicNumber;

    private Double AtomicMass;

    private Double FusionPoint;

    private Double BoilingPoint;

    @Column(nullable = true)
    private Double FreezingPoint;

    private String MeasuringUnit;

    private int Category;

    public int getCategory() {
        return Category;
    }

    public void setCategory(int category) {
        Category = category;
    }

    public String getMeasuringUnit() {
        return MeasuringUnit;
    }

    public void setMeasuringUnit(String measuringUnit) {
        MeasuringUnit = measuringUnit;
    }

    public Double getFreezingPoint() {
        return FreezingPoint;
    }

    public void setFreezingPoint(Double freezingPoint) {
        FreezingPoint = freezingPoint;
    }

    public Double getBoilingPoint() {
        return BoilingPoint;
    }

    public void setBoilingPoint(Double boilingPoint) {
        BoilingPoint = boilingPoint;
    }

    public Double getFusionPoint() {
        return FusionPoint;
    }

    public void setFusionPoint(Double fusionPoint) {
        FusionPoint = fusionPoint;
    }

    public Double getAtomicMass() {
        return AtomicMass;
    }

    public void setAtomicMass(Double atomicMass) {
        AtomicMass = atomicMass;
    }

    public int getAtomicNumber() {
        return AtomicNumber;
    }

    public void setAtomicNumber(int atomicNumber) {
        AtomicNumber = atomicNumber;
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
}
