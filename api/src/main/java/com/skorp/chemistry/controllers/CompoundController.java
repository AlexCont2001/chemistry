package com.skorp.chemistry.controllers;

import com.skorp.chemistry.models.Compounds;
import com.skorp.chemistry.services.CompoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/compounds")
public class CompoundController {

    @Autowired
    private final CompoundService compoundService;

    public CompoundController(CompoundService compoundService){
        this.compoundService = compoundService;
    }
    @GetMapping
    public ResponseEntity<ArrayList<Compounds>> GetAllCompounds(){
        return compoundService.GetAllCompounds();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Compounds>> GetCompoundById(@PathVariable int id){
        return compoundService.GetCompoundById(id);
    }
    @PostMapping
    public ResponseEntity<String> CreateCompound(@RequestBody Compounds compound){
        return  compoundService.CreateCompound(compound);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Compounds> ModifyCompound(@RequestBody Compounds compound, @PathVariable int id){
        return compoundService.ModifyCompound(compound, id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteCompound(@PathVariable int id){
        return compoundService.DeleteCompound(id);
    }
}
