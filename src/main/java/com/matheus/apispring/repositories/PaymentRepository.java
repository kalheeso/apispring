package com.matheus.apispring.repositories;

import com.matheus.apispring.domain.Adress;
import com.matheus.apispring.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}