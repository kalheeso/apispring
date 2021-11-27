package com.matheus.apispring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.matheus.apispring.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

}
