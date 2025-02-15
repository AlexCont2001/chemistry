package com.skorp.chemistry.services;

import com.skorp.chemistry.models.ElementCategories;
import com.skorp.chemistry.repositories.IElementCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ElementCategoryService {

    @Autowired
    private final IElementCategoryRepository elementCategoriesRepository;

    public ElementCategoryService(IElementCategoryRepository elementCategoriesRepository){
        this.elementCategoriesRepository = elementCategoriesRepository;
    }

    public ResponseEntity<ArrayList<ElementCategories>> GetAllElementCategories(){
        try{
            ArrayList<ElementCategories> elements = (ArrayList<ElementCategories>) elementCategoriesRepository.findAll();
            return  ResponseEntity.status(HttpStatus.OK).body(elements);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
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

    public ResponseEntity<String> CreateElementCategory(ElementCategories elementCategory){
        try{
            if(elementCategory.getName().isEmpty() || elementCategory.getDescription().isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request format. Please check the data and try again.");
            }
            elementCategoriesRepository.save(elementCategory);
            return  ResponseEntity.status(HttpStatus.CREATED).body("Created");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<ElementCategories> ModifyElementCategory(ElementCategories elementCategory, int id){
        try{

            Optional<ElementCategories> elementObj = elementCategoriesRepository.findById(id);
            if(elementObj.isEmpty()){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            if(elementCategory.getName().isEmpty() || elementCategory.getDescription().isEmpty()){
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            ElementCategories elementDB = elementObj.get();
            elementDB.setName(elementCategory.getName());
            elementDB.setDescription(elementCategory.getDescription());

            elementCategoriesRepository.save(elementDB);
            return ResponseEntity.status(HttpStatus.OK).body(elementDB);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<String> DeleteElementCategory(int id){
        try{

            Optional<ElementCategories> elementObj = elementCategoriesRepository.findById(id);
            if(elementObj.isEmpty()){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Register Not Found");
            }
            elementCategoriesRepository.deleteById(id);
            return  ResponseEntity.status(HttpStatus.OK).body("Register Deleted Successfully");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
