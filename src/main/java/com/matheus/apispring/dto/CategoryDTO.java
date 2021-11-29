package com.matheus.apispring.dto;

import com.matheus.apispring.domain.Category;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "This field can't be empty")
    @Length(min = 5, max = 65, message = "The name must have between 6 and 65 chars")
    private String name;

    private Integer id;

    public CategoryDTO(){}

    public CategoryDTO(Category cat){
        this.id = cat.getId();
        this.name = cat.getName();
    }

    public static List<CategoryDTO> parseListIntoDTOList(List<Category> categoryList){
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for(Category category: categoryList){
            CategoryDTO categoryDTO = new CategoryDTO(category);
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}