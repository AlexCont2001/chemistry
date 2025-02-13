package com.skorp.chemistry.controllers;

import com.skorp.chemistry.models.ElementCategories;
import com.skorp.chemistry.services.ElementCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/element_categories")
public class ElementCategoriesController {

    @Autowired
    private final ElementCategoriesService elementCategoriesService;

    public ElementCategoriesController(ElementCategoriesService elementCategoriesService){
        this.elementCategoriesService = elementCategoriesService;
    }

    @GetMapping
    public ArrayList<ElementCategories> GetAllElementCategories(){
        return  elementCategoriesService.GetAllElementCategories();
    }
}

