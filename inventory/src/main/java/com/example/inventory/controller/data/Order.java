package com.example.inventory.controller.data;

import java.util.HashMap;
import java.util.Map;

public class Order {
    String orderId;
    String customerId;
    int productId;

    public String getOrderId() {
        return orderId;
    }
    public String getCustomerId() {
        return customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }


}
