package com.skorp.chemistry.services;

import com.skorp.chemistry.models.ChemicalProperties;
import com.skorp.chemistry.repositories.IChemicalPropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ChemicalPropertyService {

    @Autowired
    private final IChemicalPropertyRepository chemicalPropertyRepository;

    public ChemicalPropertyService(IChemicalPropertyRepository chemicalPropertyRepository){
        this.chemicalPropertyRepository = chemicalPropertyRepository;
    }

    public ResponseEntity<ArrayList<ChemicalProperties>> GetAllChemicalProperties(){
        try{
            ArrayList<ChemicalProperties> chemicalProperties = (ArrayList<ChemicalProperties>) chemicalPropertyRepository.findAll();
            return  ResponseEntity.status(HttpStatus.OK).body(chemicalProperties);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Optional<ChemicalProperties>> GetChemicalPropertyById(int id){
        try{
            Optional<ChemicalProperties> chemicalProperty = chemicalPropertyRepository.findById(id);
            if(chemicalProperty.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(chemicalProperty);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Optional.empty());
        }
    }

    public ResponseEntity<String> CreateChemicalProperty(ChemicalProperties chemicalProperty){
        try{
            if(chemicalProperty.getName().isEmpty() || chemicalProperty.getDescription().isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request format. Please check the data and try again.");
            }
            chemicalPropertyRepository.save(chemicalProperty);
            return  ResponseEntity.status(HttpStatus.CREATED).body("Created");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<ChemicalProperties> ModifyChemicalProperty(ChemicalProperties chemicalProperty, int id){
        try{

            Optional<ChemicalProperties> chemicalObj = chemicalPropertyRepository.findById(id);
            if(chemicalObj.isEmpty()){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            if(chemicalProperty.getName().isEmpty() || chemicalProperty.getDescription().isEmpty()){
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            ChemicalProperties chemicalDB = chemicalObj.get();
            chemicalDB.setName(chemicalProperty.getName());
            chemicalDB.setDescription(chemicalProperty.getDescription());

            chemicalPropertyRepository.save(chemicalDB);
            return ResponseEntity.status(HttpStatus.OK).body(chemicalDB);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<String> DeleteChemicalProperty(int id){
        try{

            Optional<ChemicalProperties> chemicalObj = chemicalPropertyRepository.findById(id);
            if(chemicalObj.isEmpty()){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Register Not Found");
            }
            chemicalPropertyRepository.deleteById(id);
            return  ResponseEntity.status(HttpStatus.OK).body("Register Deleted Successfully");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
