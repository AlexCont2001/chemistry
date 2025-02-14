package com.skorp.chemistry.services;

import com.skorp.chemistry.models.ElementCategories;
import com.skorp.chemistry.models.Elements;
import com.skorp.chemistry.repositories.IElementCategoriesRepository;
import com.skorp.chemistry.repositories.IElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ElementService {

    @Autowired
    private final IElementRepository elementRepository;

    public ElementService(IElementRepository elementRepository){
        this.elementRepository = elementRepository;
    }

    public ResponseEntity<ArrayList<Elements>> GetAllElements(){
        try{
            ArrayList<Elements> elements = (ArrayList<Elements>) elementRepository.findAll();
            return  ResponseEntity.status(HttpStatus.OK).body(elements);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Optional<Elements>> GetElementById(int id){
        try{
            Optional<Elements> element = elementRepository.findById(id);
            if(element.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(element);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Optional.empty());
        }
    }

    public ResponseEntity<String> CreateElement(Elements elements){
        try{
            if(elements.getName().isEmpty() || elements.getAtomicNumber() <= 0 || elements.getAtomicMass() == null || elements.getFusionPoint() == null
                || elements.getBoilingPoint() == null  || elements.getMeasuringUnit().isEmpty() || elements.getCategory() <= 0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request format. Please check the data and try again.");
            }
            elementRepository.save(elements);
            return  ResponseEntity.status(HttpStatus.CREATED).body("Created");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Elements> ModifyElement(Elements elements, int id){
        try{

            Optional<Elements> elementObj = elementRepository.findById(id);
            if(elementObj.isEmpty()){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            if(elements.getName().isEmpty() || elements.getAtomicNumber() <= 0 || elements.getAtomicMass() == null || elements.getFusionPoint() == null
                    || elements.getBoilingPoint() == null || elements.getFreezingPoint() == null || elements.getMeasuringUnit().isEmpty() || elements.getCategory() <= 0){
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            Elements elementDB = elementObj.get();
            elementDB.setName(elements.getName());
            elementDB.setAtomicNumber(elements.getAtomicNumber());
            elementDB.setAtomicMass(elements.getAtomicMass());
            elementDB.setFusionPoint(elements.getFusionPoint());
            elementDB.setBoilingPoint(elements.getBoilingPoint());
            elementDB.setFreezingPoint(elements.getFreezingPoint());
            elementDB.setMeasuringUnit(elements.getMeasuringUnit());
            elementDB.setCategory(elements.getCategory());

            elementRepository.save(elementDB);
            return ResponseEntity.status(HttpStatus.OK).body(elementDB);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<String> DeleteElement(int id){
        try{

            Optional<Elements> elementObj = elementRepository.findById(id);
            if(elementObj.isEmpty()){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Register Not Found");
            }
            elementRepository.deleteById(id);
            return  ResponseEntity.status(HttpStatus.OK).body("Register Deleted Successfully");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
