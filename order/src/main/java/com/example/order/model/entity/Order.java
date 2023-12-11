package com.example.order.model.entity;

import com.example.order.model.VO.ProductVO;
import com.example.order.model.constant.FieldNames;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Document(collection = "orders")
public class Order {
    @Id
    @Field("orderId")
    String orderId;

    String customerId;

    ProductVO product;

    public String getOrderId() {
        return orderId;
    }
    public String getCustomerId() {
        return customerId;
    }
    public ProductVO getProduct() {
        return product;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public void setProduct(ProductVO product) {
        this.product = product;
    }

}
