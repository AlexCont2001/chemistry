package com.skorp.chemistry.controllers;

import com.skorp.chemistry.models.ChemicalProperties;
import com.skorp.chemistry.models.Compositions;
import com.skorp.chemistry.services.CompositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/compositions")
public class CompositionController {

    @Autowired
    private final CompositionService compositionService;

    public CompositionController(CompositionService compositionService){
        this.compositionService = compositionService;
    }

    @GetMapping
    public ResponseEntity<ArrayList<Compositions>> GetAllCompositions(){
        return compositionService.GetAllCompositions();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Compositions>> GetCompositionById(@PathVariable int id){
        return compositionService.GetCompositionById(id);
    }
    @PostMapping
    public ResponseEntity<String> CreateComposition(@RequestBody Compositions composition){
        return  compositionService.CreateComposition(composition);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Compositions> ModifyComposition(@RequestBody Compositions composition, @PathVariable int id){
        return compositionService.ModifyComposition(composition, id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteComposition(@PathVariable int id){
        return compositionService.DeleteComposition(id);
    }
}
