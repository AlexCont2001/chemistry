package com.skorp.chemistry.repositories;

import com.skorp.chemistry.models.Elements;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IElementRepository extends JpaRepository<Elements, Integer> {
}
