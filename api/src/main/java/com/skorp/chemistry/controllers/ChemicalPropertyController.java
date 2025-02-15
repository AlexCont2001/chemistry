package com.skorp.chemistry.controllers;

import com.skorp.chemistry.models.ChemicalProperties;
import com.skorp.chemistry.services.ChemicalPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/chemical_properties")
public class ChemicalPropertyController {

    @Autowired
    private final ChemicalPropertyService chemicalPropertyService;

    public ChemicalPropertyController(ChemicalPropertyService chemicalPropertyService){
        this.chemicalPropertyService = chemicalPropertyService;
    }

    @GetMapping
    public ResponseEntity<ArrayList<ChemicalProperties>> GetAllChemicalProperties(){
        return chemicalPropertyService.GetAllChemicalProperties();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ChemicalProperties>> GetChemicalPropertyById(@PathVariable int id){
        return chemicalPropertyService.GetChemicalPropertyById(id);
    }
    @PostMapping
    public ResponseEntity<String> CreateChemicalProperty(@RequestBody ChemicalProperties chemicalProperty){
        return  chemicalPropertyService.CreateChemicalProperty(chemicalProperty);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ChemicalProperties> ModifyChemicalProperty(@RequestBody ChemicalProperties chemicalProperty, @PathVariable int id){
        return chemicalPropertyService.ModifyChemicalProperty(chemicalProperty, id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteChemicalProperty(@PathVariable int id){
        return chemicalPropertyService.DeleteChemicalProperty(id);
    }
}
