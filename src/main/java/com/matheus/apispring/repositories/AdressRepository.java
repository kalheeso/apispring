package com.matheus.apispring.repositories;

import com.matheus.apispring.domain.Adress;
import com.matheus.apispring.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Integer> {

}