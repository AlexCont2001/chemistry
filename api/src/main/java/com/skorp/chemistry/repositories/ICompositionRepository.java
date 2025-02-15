package com.skorp.chemistry.repositories;

import com.skorp.chemistry.models.Compositions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompositionRepository extends JpaRepository<Compositions,Integer> {
}
