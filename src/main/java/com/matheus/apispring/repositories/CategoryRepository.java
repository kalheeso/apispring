package com.matheus.apispring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheus.apispring.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	
}
