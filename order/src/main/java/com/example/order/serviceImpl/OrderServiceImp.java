package com.example.order.serviceImpl;

import com.example.order.controller.data.PurchaseOrder;
import com.example.order.dao.api.OrderRepository;
import com.example.order.model.VO.ProductVO;
import com.example.order.model.entity.Order;
import com.example.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service("ordService")
public class OrderServiceImp implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    String baseInventoryUrl = "http://localhost:8081/inventory/";

    @Autowired
    RestTemplate restTemplate;


    @Override
    public List<Order> getCustomerOrderHistory(String customerId) {
        List<Order> orderList =
                orderRepository.findAllByCustomerId(customerId);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(orderList, List.class);
    }

    public String generateUniqueOrderId() {
        return UUID.randomUUID().toString();
    }


    @Override
    public List<ProductVO> getProductDetailsById(Integer productId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseInventoryUrl + "products")
                .queryParam("productId", productId).build();
        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<ProductVO>>() {
        }).getBody();
    }


    @Override
    public String placeOrder(PurchaseOrder p) {
        ObjectMapper objectMapper = new ObjectMapper();
        Order ord = new Order();
        List<ProductVO> prodList = getProductDetailsById(p.getProductId());
        if (!prodList.isEmpty()) {
            ProductVO prod = prodList.get(0);
            if (prod.getQuantity() > p.getQuantity()) {
                int quantity = prod.getQuantity() - p.getQuantity();
                ord.setCustomerId(p.getCustomerId());
                ord.setOrderId(generateUniqueOrderId());
                prod.setQuantity(p.getQuantity());
                ord.setProduct(prod);
                orderRepository.save(objectMapper.convertValue(ord, Order.class));
                updateProductDetails(p.getProductId(), prod, quantity);
                return "added";
            } else {
                return "order quantity exceed available quantity";
            }
        } else {
            return "product unavailable";
        }

    }

    @Override
    public String updateProductDetails(Integer productId, ProductVO p, int quantity) {
        p.setQuantity(quantity);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity(p, headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseInventoryUrl + "product")
                .queryParam("productId", productId).build();
        return restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, entity, String.class).getBody();
    }
}