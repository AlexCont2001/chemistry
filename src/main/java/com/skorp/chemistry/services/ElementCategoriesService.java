package com.skorp.chemistry.services;

import com.skorp.chemistry.models.ElementCategories;
import com.skorp.chemistry.repositories.IElementCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ElementCategoriesService {

    @Autowired
    private final IElementCategoriesRepository elementCategoriesRepository;

    public ElementCategoriesService(IElementCategoriesRepository elementCategoriesRepository){
        this.elementCategoriesRepository = elementCategoriesRepository;
    }

    public ArrayList<ElementCategories> GetAllElementCategories(){
        return (ArrayList<ElementCategories>) elementCategoriesRepository.findAll();
    }
}
