package com.example.order.controller;

import com.example.order.controller.data.Product;
import com.example.order.controller.data.PurchaseOrder;
import com.example.order.model.entity.Order;
import com.example.order.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class HttpMethodController {

    @Autowired
    OrderService ordService;

    @GetMapping("/orderhistory")
    public List<Order> getOrderHistory(@RequestParam(value = "customerId") String customerId){
        return ordService.getCustomerOrderHistory(customerId);
    }

    @PostMapping("/place-order")
    public String saveProductDetails(@RequestBody PurchaseOrder ord){
        return ordService.placeOrder(ord);
    }


}
