package com.example.order.service;

//import com.example.order.controller.data.Order;
//import com.example.order.controller.data.Product;
import com.example.order.controller.data.PurchaseOrder;
import com.example.order.model.VO.ProductVO;
import com.example.order.model.entity.Order;
//import com.example.order.model.entity.Product;

import java.util.List;

public interface OrderService {

    List<Order> getCustomerOrderHistory(String customerId);
    String placeOrder(PurchaseOrder ord);
    List<ProductVO> getProductDetailsById(Integer productId);
    String updateProductDetails(Integer productId, ProductVO p, int quantity);
}
