package com.example.order.controller.data;

public class PurchaseOrder {
    String orderId;
    String customerId;
    int productId;
    int quantity;
    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
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
    public void SetQuantity(int quantity) {
        this.quantity = quantity;
    }


}
