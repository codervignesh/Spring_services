package com.example.customergateway.model.vo;

import lombok.Data;

@Data
public class ProductVO {
    private Long id;

    private String name;

    private double price;

    private int quantity;

    private CategoryVO category;

}
