package com.example.customergateway.serviceImpl;

import com.example.customergateway.model.vo.OrderVO;
import com.example.customergateway.model.vo.PurchaseOrderVO;
import com.example.customergateway.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service("OrderService")
public class OrderServiceImp implements OrderService {
    String baseOrderUrl = "http://localhost:8082/order/";

    @Autowired
    RestTemplate restTemplate;


    @Override
    public List<OrderVO> getOrderHistory(String customerId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseOrderUrl + "orderhistory/")
                .queryParam("customerId", customerId).build();
        return restTemplate.exchange(builder.toUriString(),HttpMethod.GET, entity, List.class).getBody();
    }

    @Override
    public String placeOrder(PurchaseOrderVO ord) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PurchaseOrderVO> entity = new HttpEntity(ord, headers);
        return restTemplate.exchange(baseOrderUrl+"place-order", HttpMethod.POST, entity, String.class).getBody();
    }


}

