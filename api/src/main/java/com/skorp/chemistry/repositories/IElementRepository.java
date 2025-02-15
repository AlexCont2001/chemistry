package com.skorp.chemistry.repositories;

import com.skorp.chemistry.models.Elements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IElementRepository extends JpaRepository<Elements, Integer> {
}
