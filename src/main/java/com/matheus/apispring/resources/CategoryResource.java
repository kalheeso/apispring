package com.matheus.apispring.resources;

import com.matheus.apispring.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.matheus.apispring.domain.Category;
import com.matheus.apispring.services.CategoryService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> findById(
            @PathVariable Integer id) {
        Category obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = service.fromDTO(categoryDTO);
        service.create(category);
        //method save from repository returns an object
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(category.getId())
                .toUri();
        //uri recebe uri que busca por id objeto que acabou de ser criado(fica no header)
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = service.fromDTO(categoryDTO);
        category.setId(id);
        category = service.update(category);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<Category> listCat = service.findAll();
        List<CategoryDTO> listCatDTO = CategoryDTO.parseListIntoDTOList(listCat);
        return ResponseEntity.ok().body(listCatDTO);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/page")
    public ResponseEntity<Page<CategoryDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        Page<Category> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoryDTO> listDTO = list.map(category -> new CategoryDTO(category));
        return ResponseEntity.ok().body(listDTO);
    }
}