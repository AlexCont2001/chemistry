package com.skorp.chemistry.controllers;

import com.skorp.chemistry.models.ElementCategories;
import com.skorp.chemistry.services.ElementCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/element_categories")
public class ElementCategoriesController {

    @Autowired
    private final ElementCategoriesService elementCategoriesService;

    public ElementCategoriesController(ElementCategoriesService elementCategoriesService){
        this.elementCategoriesService = elementCategoriesService;
    }

    @GetMapping
    public ResponseEntity<ArrayList<ElementCategories>> GetAllElementCategories(){
        return  elementCategoriesService.GetAllElementCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ElementCategories>> GetElementCategoryById(@PathVariable int id) {
        return elementCategoriesService.GetElementCategoryById(id);
    }

    @PostMapping
    public ResponseEntity<String> CreateElementCategory(@RequestBody ElementCategories elementCategory){
        return elementCategoriesService.CreateElementCategory(elementCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ElementCategories> ModifyElementCategory(@RequestBody ElementCategories elementCategory, @PathVariable int id){
        return elementCategoriesService.ModifyElementCategory(elementCategory, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteElementCategory(@PathVariable int id){
        return  elementCategoriesService.DeleteElementCategory(id);
    }
}

