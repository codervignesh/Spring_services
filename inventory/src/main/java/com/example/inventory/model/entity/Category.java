package com.example.inventory.model.entity;


import com.example.inventory.config.CategorySerializer;
import com.example.inventory.model.constant.FieldNames;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = FieldNames.CATEGORY_T)
//@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonSerialize(using = CategorySerializer.class)
public class Category {
    @Id
    @Column(name = FieldNames.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = FieldNames.NAME, nullable  = false)
    private String name;

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> product = new ArrayList<>();

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
