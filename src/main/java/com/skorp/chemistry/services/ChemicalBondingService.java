package com.skorp.chemistry.services;

import com.skorp.chemistry.models.ChemicalBondings;
import com.skorp.chemistry.repositories.IChemicalBondingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ChemicalBondingService {
    @Autowired
    private final IChemicalBondingRepository chemicalBondingRepository;

    public ChemicalBondingService(IChemicalBondingRepository chemicalBondingRepository){
        this.chemicalBondingRepository = chemicalBondingRepository;
    }
    public ResponseEntity<ArrayList<ChemicalBondings>> GetAllChemicalBondings(){
        try{
            ArrayList<ChemicalBondings> chemicalBondings = (ArrayList<ChemicalBondings>) chemicalBondingRepository.findAll();
            return  ResponseEntity.status(HttpStatus.OK).body(chemicalBondings);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Optional<ChemicalBondings>> GetChemicalBondingById(int id){
        try{
            Optional<ChemicalBondings> chemicalBonding = chemicalBondingRepository.findById(id);
            if(chemicalBonding.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(chemicalBonding);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Optional.empty());
        }
    }

    public ResponseEntity<String> CreateChemicalBonding(ChemicalBondings chemicalBonding){
        try{
            if(chemicalBonding.getName().isEmpty() || chemicalBonding.getDescription().isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request format. Please check the data and try again.");
            }
            chemicalBondingRepository.save(chemicalBonding);
            return  ResponseEntity.status(HttpStatus.CREATED).body("Created");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<ChemicalBondings> ModifyChemicalBonding(ChemicalBondings chemicalBonding, int id){
        try{

            Optional<ChemicalBondings> chemicalObj = chemicalBondingRepository.findById(id);
            if(chemicalObj.isEmpty()){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            if(chemicalBonding.getName().isEmpty() || chemicalBonding.getDescription().isEmpty()){
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            ChemicalBondings chemicalDB = chemicalObj.get();
            chemicalDB.setName(chemicalBonding.getName());
            chemicalDB.setDescription(chemicalBonding.getDescription());

            chemicalBondingRepository.save(chemicalDB);
            return ResponseEntity.status(HttpStatus.OK).body(chemicalDB);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<String> DeleteChemicalBonding(int id){
        try{

            Optional<ChemicalBondings> chemicalObj = chemicalBondingRepository.findById(id);
            if(chemicalObj.isEmpty()){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Register Not Found");
            }
            chemicalBondingRepository.deleteById(id);
            return  ResponseEntity.status(HttpStatus.OK).body("Register Deleted Successfully");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
