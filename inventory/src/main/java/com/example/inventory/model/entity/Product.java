package com.example.inventory.model.entity;

import com.example.inventory.config.ProductSerializer;
import com.example.inventory.model.constant.FieldNames;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = FieldNames.PRODUCT_T)
//@JsonSerialize(using = ProductSerializer.class)
public class Product {

    @Id
    @Column(name = FieldNames.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = FieldNames.NAME, nullable = false)
    private String name;

    @Column(name = FieldNames.PRICE, nullable = false)
    private double price;

    @Column(name = FieldNames.QUANTITY, nullable = false)
    private int quantity;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = FieldNames.CATEGORY_ID, nullable = false)
    private Category category;


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Category getCategory() {
        return category;
    }


}
