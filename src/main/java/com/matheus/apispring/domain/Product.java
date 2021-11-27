package com.matheus.apispring.domain;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "PRODUCT_CATEGORY", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<Category>();


    @JsonIgnore
    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> itens = new HashSet<>();

    public Product() {
    }

    public Product(Integer id, String name, Double price) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // tudo que é começado com get é serializado por isso o @JsonIgnore!!
    @JsonIgnore
    public List<Order> getOrders() {
        List<Order> list = new ArrayList<>();
        for (OrderItem x : itens) {
            list.add(x.getOrder());
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Set<OrderItem> getItens() {
        return itens;
    }

    public void setItens(Set<OrderItem> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}