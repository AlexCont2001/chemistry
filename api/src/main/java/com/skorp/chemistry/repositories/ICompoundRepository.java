package com.skorp.chemistry.repositories;


import com.skorp.chemistry.models.Compounds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompoundRepository extends JpaRepository<Compounds,Integer> {
}
