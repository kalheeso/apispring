package com.matheus.apispring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.matheus.apispring.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}