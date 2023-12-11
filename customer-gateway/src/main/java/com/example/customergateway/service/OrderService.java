package com.example.customergateway.service;

import com.example.customergateway.model.vo.OrderVO;
import com.example.customergateway.model.vo.PurchaseOrderVO;

import java.util.List;

public interface OrderService {

    List<OrderVO> getOrderHistory(String customerId);

    String placeOrder(PurchaseOrderVO ord);
}
