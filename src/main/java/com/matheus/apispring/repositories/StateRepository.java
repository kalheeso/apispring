package com.matheus.apispring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.matheus.apispring.domain.Product;
import com.matheus.apispring.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}