package com.skorp.chemistry.controllers;

import com.skorp.chemistry.models.ChemicalBondings;
import com.skorp.chemistry.services.ChemicalBondingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/chemical_bondings")
public class ChemicalBondingController {

    @Autowired
    private final ChemicalBondingService chemicalBondingService;

    public ChemicalBondingController(ChemicalBondingService chemicalBondingService){
        this.chemicalBondingService = chemicalBondingService;
    }

    @GetMapping
    public ResponseEntity<ArrayList<ChemicalBondings>> GetAllChemicalBondings(){
        return  chemicalBondingService.GetAllChemicalBondings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ChemicalBondings>> GetChemicalBondingById(@PathVariable int id) {
        return chemicalBondingService.GetChemicalBondingById(id);
    }

    @PostMapping
    public ResponseEntity<String> CreateChemicalBonding(@RequestBody ChemicalBondings chemicalBonding){
        return chemicalBondingService.CreateChemicalBonding(chemicalBonding);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChemicalBondings> ModifyChemicalBonding(@RequestBody ChemicalBondings chemicalBonding, @PathVariable int id){
        return chemicalBondingService.ModifyChemicalBonding(chemicalBonding, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteChemicalBonding(@PathVariable int id){
        return  chemicalBondingService.DeleteChemicalBonding(id);
    }
}
