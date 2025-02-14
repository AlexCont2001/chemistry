package com.skorp.chemistry.services;

import com.skorp.chemistry.models.Compositions;
import com.skorp.chemistry.models.Compounds;
import com.skorp.chemistry.repositories.ICompoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CompoundService {

    @Autowired
    private final ICompoundRepository compoundRepository;

    public CompoundService(ICompoundRepository compoundRepository){
        this.compoundRepository = compoundRepository;
    }

    public ResponseEntity<ArrayList<Compounds>> GetAllCompounds(){
        try{
            ArrayList<Compounds> compounds = (ArrayList<Compounds>) compoundRepository.findAll();
            return  ResponseEntity.status(HttpStatus.OK).body(compounds);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Optional<Compounds>> GetCompoundById (int id){
        try{
            Optional<Compounds> compound = compoundRepository.findById(id);
            if(compound.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(compound);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Optional.empty());
        }
    }

    public ResponseEntity<String> CreateCompound(Compounds compound){
        try{
            if(compound.getName().isEmpty() || compound.getIupacName().isEmpty() || compound.getFormula().isEmpty()
                || compound.getMolarMass() == null || compound.getComposition() <= 0 || compound.getChemicalBonding() <= 0 || compound.getChemicalProperty() <= 0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request format. Please check the data and try again.");
            }
            compoundRepository.save(compound);
            return  ResponseEntity.status(HttpStatus.CREATED).body("Created");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Compounds> ModifyCompound(Compounds compound, int id){
        try{

            Optional<Compounds> compoundObj = compoundRepository.findById(id);
            if(compoundObj.isEmpty()){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            if(compound.getName().isEmpty() || compound.getIupacName().isEmpty() || compound.getFormula().isEmpty()
                    || compound.getMolarMass() == null || compound.getComposition() <= 0 || compound.getChemicalBonding() <= 0 || compound.getChemicalProperty() <= 0){
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            Compounds compoundDB = compoundObj.get();
            compoundDB.setName(compound.getName());
            compoundDB.setIupacName(compound.getIupacName());
            compoundDB.setFormula(compound.getFormula());
            compoundDB.setMolarMass(compound.getMolarMass());
            compoundDB.setComposition(compound.getComposition());
            compoundDB.setChemicalBonding(compound.getChemicalBonding());
            compoundDB.setChemicalProperty(compound.getChemicalProperty());

            compoundRepository.save(compoundDB);
            return ResponseEntity.status(HttpStatus.OK).body(compoundDB);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<String> DeleteCompound(int id){
        try{

            Optional<Compounds> compoundObj = compoundRepository.findById(id);
            if(compoundObj.isEmpty()){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Register Not Found");
            }
            compoundRepository.deleteById(id);
            return  ResponseEntity.status(HttpStatus.OK).body("Register Deleted Successfully");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
