package com.example.customergateway.model.vo;

import com.example.customergateway.model.vo.ProductVO;

import java.util.HashMap;
import java.util.Map;

public class OrderVO {
    String orderId;

    String customerId;

    ProductVO product;

    public String getOrderId() {
        return orderId;
    }
    public String getCustomerId() {
        return customerId;
    }

    public ProductVO getProductId() {
        return product;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setProductId(ProductVO product) {
        this.product = product;
    }


}