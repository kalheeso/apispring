package com.matheus.apispring.services;

import com.matheus.apispring.domain.Order;
import com.matheus.apispring.repositories.OrderRepository;
import com.matheus.apispring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    public Order find(Integer id) {
        Optional<Order> obj = repo.findById(id);
        return obj.orElseThrow(
                () -> new ObjectNotFoundException("Object not found! ID: " + id
                        + ". Object type: " + Order.class.getName()));
    }
}