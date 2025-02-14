package com.skorp.chemistry.services;

import com.skorp.chemistry.models.Compositions;
import com.skorp.chemistry.repositories.ICompositionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CompositionService {

    private final ICompositionRepository compositionRepository;

    public CompositionService(ICompositionRepository compositionRepository){
        this.compositionRepository = compositionRepository;
    }

    public ResponseEntity<ArrayList<Compositions>> GetAllCompositions(){
        try{
            ArrayList<Compositions> compositions = (ArrayList<Compositions>) compositionRepository.findAll();
            return  ResponseEntity.status(HttpStatus.OK).body(compositions);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Optional<Compositions>> GetCompositionById (int id){
        try{
            Optional<Compositions> composition = compositionRepository.findById(id);
            if(composition.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(composition);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Optional.empty());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Optional.empty());
        }
    }

    public ResponseEntity<String> CreateComposition(Compositions composition){
        try{
            if(composition.getName().isEmpty() || composition.getDescription().isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request format. Please check the data and try again.");
            }
            compositionRepository.save(composition);
            return  ResponseEntity.status(HttpStatus.CREATED).body("Created");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Compositions> ModifyComposition(Compositions composition, int id){
        try{

            Optional<Compositions> compositionObj = compositionRepository.findById(id);
            if(compositionObj.isEmpty()){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            if(composition.getName().isEmpty() || composition.getDescription().isEmpty()){
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            Compositions compositionDB = compositionObj.get();
            compositionDB.setName(composition.getName());
            compositionDB.setDescription(composition.getDescription());

            compositionRepository.save(compositionDB);
            return ResponseEntity.status(HttpStatus.OK).body(compositionDB);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<String> DeleteComposition(int id){
        try{

            Optional<Compositions> compositionObj = compositionRepository.findById(id);
            if(compositionObj.isEmpty()){
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Register Not Found");
            }
            compositionRepository.deleteById(id);
            return  ResponseEntity.status(HttpStatus.OK).body("Register Deleted Successfully");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
