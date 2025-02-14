package com.skorp.chemistry.repositories;

import com.skorp.chemistry.models.ChemicalProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChemicalPropertyRepository extends JpaRepository<ChemicalProperties,Integer> {
}
