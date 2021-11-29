package com.matheus.apispring.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.matheus.apispring.dto.CategoryDTO;
import com.matheus.apispring.services.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.matheus.apispring.domain.Category;
import com.matheus.apispring.repositories.CategoryRepository;
import com.matheus.apispring.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    public Category findById(Integer id) {
        Optional<Category> obj = repo.findById(id);
        return obj.get();
    }

    public Category create(Category category) {
        category.setId(null);        //garantees that the save method will create an object(if the ID isn't null it could update an object)
        return repo.save(category);
    }

    public Category update(Category category) {
        Category updateCategory = repo.findById(category.getId()).get();
        updateData(category, updateCategory);
        return repo.save(updateCategory);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("It's not possible to delete a category that has associated products");
        }
    }

    public List<Category> findAll(){
        return repo.findAll();
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Category fromDTO(CategoryDTO categoryDTO){
        return new Category(categoryDTO.getId(), categoryDTO.getName());
    }

    public void updateData(Category category, Category updateCategory){
        updateCategory.setName(category.getName());
        updateCategory.setProducts(category.getProducts());
    }
}