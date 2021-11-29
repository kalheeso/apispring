package com.matheus.apispring.resources;

import com.matheus.apispring.domain.Category;
import com.matheus.apispring.domain.Client;
import com.matheus.apispring.dto.CategoryDTO;
import com.matheus.apispring.dto.ClientDTO;
import com.matheus.apispring.dto.ClientPOSTDTO;
import com.matheus.apispring.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> find(@PathVariable Integer id) {
        Client client = service.findById(id);
        return ResponseEntity.ok().body(client);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody ClientPOSTDTO clientDTO) {
        Client client = service.fromDTO(clientDTO);
        service.create(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id,@Valid @RequestBody ClientDTO clientDTO) {
        Client client = service.fromDTO(clientDTO);
        client.setId(id);
        service.update(client);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<Client> listClient = service.findAll();
        List<ClientDTO> listClientDTO = ClientDTO.parseListIntoDTOList(listClient);
        return ResponseEntity.ok().body(listClientDTO);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/page")
    public ResponseEntity<Page<ClientDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        Page<Client> listClient = service.findPage(page, linesPerPage, orderBy, direction);
        Page<ClientDTO> listClientDTO = listClient.map(client -> new ClientDTO(client));
        return ResponseEntity.ok().body(listClientDTO);
    }

}