package com.skorp.chemistry.controllers;

import com.skorp.chemistry.models.ElementCategories;
import com.skorp.chemistry.models.Elements;
import com.skorp.chemistry.services.ElementCategoryService;
import com.skorp.chemistry.services.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/elements")
public class ElementsController {

    @Autowired
    private final ElementService elementService;

    public ElementsController(ElementService elementService){
        this.elementService = elementService;
    }

    @GetMapping
    public ResponseEntity<ArrayList<Elements>> GetAllElements(){
        return  elementService.GetAllElements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Elements>> GetElementById(@PathVariable int id) {
        return elementService.GetElementById(id);
    }

    @PostMapping
    public ResponseEntity<String> CreateElement(@RequestBody Elements element){
        return elementService.CreateElement(element);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Elements> ModifyElement(@RequestBody Elements element, @PathVariable int id){
        return elementService.ModifyElement(element, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteElementCategory(@PathVariable int id){
        return  elementService.DeleteElement(id);
    }
}
