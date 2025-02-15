package com.skorp.chemistry.repositories;

import com.skorp.chemistry.models.ChemicalBondings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChemicalBondingRepository extends JpaRepository<ChemicalBondings,Integer> {
}
