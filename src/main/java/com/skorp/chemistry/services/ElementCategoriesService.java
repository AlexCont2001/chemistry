package com.skorp.chemistry.services;

import com.skorp.chemistry.models.ElementCategories;
import com.skorp.chemistry.repositories.IElementCategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ElementCategoriesService {

    @Autowired
    private final IElementCategoriesRepository elementCategoriesRepository;

    public ElementCategoriesService(IElementCategoriesRepository elementCategoriesRepository){
        this.elementCategoriesRepository = elementCategoriesRepository;
    }

    public ResponseEntity<ArrayList<ElementCategories>> GetAllElementCategories(){
        try{
            ArrayList<ElementCategories> elements = (ArrayList<ElementCategories>) elementCategoriesRepository.findAll();
            return  ResponseEntity.status(HttpStatus.OK).body(elements);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
        }
    }

    public ResponseEntity<Optional<ElementCategories>> GetElementCategoryById(int id){
        try{
            Optional<ElementCategories> elementCategory = elementCategoriesRepository.findById(id);
            if(elementCategory.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(elementCategory);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Optional.empty());
        }
    }
}
