package com.skorp.chemistry.repositories;

import com.skorp.chemistry.models.ElementCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IElementCategoriesRepository extends JpaRepository<ElementCategories,Integer> {
}
