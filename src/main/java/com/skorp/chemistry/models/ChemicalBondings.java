package com.skorp.chemistry.models;

import jakarta.persistence.*;

@Entity
@Table(name = "chemical_bondings")
public class ChemicalBondings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String Name;
    private String Description;

    public int getId(){
        return Id;
    }
    public void setId(int id){
        Id = id;
    }
    public String getName(){
        return Name;
    }
    public void setName(String name){
        Name = name;
    }
    public String getDescription(){
        return Description;
    }
    public void setDescription(String description){
        Description = description;
    }
}
