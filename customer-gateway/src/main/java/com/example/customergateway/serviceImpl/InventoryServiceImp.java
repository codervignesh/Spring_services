package com.example.customergateway.serviceImpl;

//import com.example.customergateway.controller.data.Customer;
//import com.example.customergateway.controller.data.Product;
import com.example.customergateway.model.vo.CategoryVO;
import com.example.customergateway.model.vo.ProductVO;
import com.example.customergateway.service.InventoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Arrays;
import java.util.List;

@Service("InventoryService")
public class InventoryServiceImp implements InventoryService {

    String baseInventoryUrl = "http://localhost:8081/inventory/";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public List<CategoryVO> getCategoryDetails(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseInventoryUrl + "category/");
        return restTemplate.exchange(builder.build().toUriString(),HttpMethod.GET, entity, List.class).getBody();
    }


    @Override
    public List<ProductVO> getProductDetails(Integer productId, String category, String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseInventoryUrl + "products/");

        if (productId != 0)
            builder.queryParam("productId", productId);

        if (!category.equals("null"))
            builder.queryParam("category", category);

        if (!name.equals("null"))
            builder.queryParam("name", name);

        return restTemplate.exchange(builder.build().toUriString(),HttpMethod.GET, entity, List.class).getBody();
    }

    @Override
    public String addProductDetails(ProductVO p) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity(p, headers);
        return restTemplate.exchange(baseInventoryUrl+"product", HttpMethod.POST, entity, String.class).getBody();
    }

    @Override
    public String updateProductDetails(Integer productId, ProductVO p){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity(p, headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseInventoryUrl + "product")
                .queryParam("productId", productId).build();
        return restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, entity, String.class).getBody();
    }

    @Override
    public String deleteProductDetails(Integer productId){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseInventoryUrl + "product")
                .queryParam("productId", productId).build();
        return restTemplate.exchange(builder.toUriString(),HttpMethod.DELETE, entity, String.class).getBody();
    }

    @Override
    public void sendMessage(ProductVO prod) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        kafkaTemplate.send("com.quinbay.product.create" ,objectMapper.writeValueAsString(prod));
    }


}

