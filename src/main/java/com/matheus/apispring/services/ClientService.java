package com.matheus.apispring.services;

import com.matheus.apispring.domain.Client;
import com.matheus.apispring.repositories.ClientRepository;
import com.matheus.apispring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    public Client find(Integer id) {
        Optional<Client> obj = repo.findById(id);
        return obj.orElseThrow(
                () -> new ObjectNotFoundException("Object not found! ID: " + id
                        + ". Object type: " + Client.class.getName()));
    }
}