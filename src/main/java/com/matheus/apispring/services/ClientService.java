package com.matheus.apispring.services;

import com.matheus.apispring.domain.Adress;
import com.matheus.apispring.domain.Category;
import com.matheus.apispring.domain.City;
import com.matheus.apispring.domain.Client;
import com.matheus.apispring.domain.enums.ClientType;
import com.matheus.apispring.dto.CategoryDTO;
import com.matheus.apispring.dto.ClientDTO;
import com.matheus.apispring.dto.ClientPOSTDTO;
import com.matheus.apispring.repositories.AdressRepository;
import com.matheus.apispring.repositories.CityRepository;
import com.matheus.apispring.repositories.ClientRepository;
import com.matheus.apispring.services.exceptions.DataIntegrityException;
import com.matheus.apispring.services.exceptions.NullFieldException;
import com.matheus.apispring.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class ClientService {

    @Autowired
    private ClientRepository repo;

    @Autowired
    private CityRepository cityRepo;

    @Autowired
    private AdressRepository addressRepo;

    public Client create(Client client) {
        client.setId(null);
        client = repo.save(client);
        addressRepo.saveAll(client.getAdresses());
        return client;
    }

    public Client findById(Integer id) {
        Optional<Client> client = repo.findById(id);
        return client.get();
    }

    public Client update(Client client) {
            Client clientUpdate = repo.findById(client.getId()).get();
            updateData(client, clientUpdate);
            return repo.save(clientUpdate);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("It's not possible to delete a client that has associated objects");
        }
    }

    public List<Client> findAll(){
        return repo.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Client fromDTO(ClientDTO clientDTO){
        return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null);
    }

    public Client fromDTO(ClientPOSTDTO clientDTO){
        Client client = new Client(null, clientDTO.getName(), clientDTO.getEmail(), clientDTO.getCpfOrCnpj(), ClientType.toEnum(clientDTO.getType()));
        City city = cityRepo.findById(clientDTO.getCityID()).get();
        Adress adress = new Adress(null, clientDTO.getPublicPlace(), clientDTO.getNumber(), clientDTO.getComplement(), clientDTO.getNeighborhood(),
                clientDTO.getZipCode(), client, city);
        client.getAdresses().add(adress);
        client.getPhoneNumbers().add(clientDTO.getPhoneNumber1());
        if(clientDTO.getPhoneNumber2() != null){
            client.getPhoneNumbers().add(clientDTO.getPhoneNumber2());
        }
        if(clientDTO.getPhoneNumber3() != null){
            client.getPhoneNumbers().add(clientDTO.getPhoneNumber3());
        }
        return client;
    }

    private void updateData(Client client, Client clientUpdate){
        clientUpdate.setName(client.getName());
        clientUpdate.setEmail(client.getEmail());
    }
}