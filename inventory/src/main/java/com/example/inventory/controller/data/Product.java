package com.example.inventory.controller.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    @JsonProperty("id")
    long productId;
    String productName;
    String category;
    float price;
    int quantity;

    public long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() { return category; }

    public float getPrice() { return price; }

    public int getQuantity() { return quantity; }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategory(String category) { this.category = category; }

    public void setPrice(float price) { this.price = price; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

}