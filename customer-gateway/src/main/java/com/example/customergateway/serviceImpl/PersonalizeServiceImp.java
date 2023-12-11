package com.example.customergateway.serviceImpl;

import com.example.customergateway.model.vo.ProductVO;
import com.example.customergateway.model.vo.RecommendVO;
import com.example.customergateway.service.InventoryService;
import com.example.customergateway.service.PersonalizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("PersonalizeService")
public class PersonalizeServiceImp implements PersonalizeService {
    String basePersonalizeUrl = "http://localhost:8083/personalize/";

    @Autowired
    InventoryService inventoryService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<ProductVO> getRecommendationList(Integer customerId) {
        List<RecommendVO> recommendList = getRecommendCategory(customerId);
        List<ProductVO> prodList = new ArrayList<ProductVO>();
        if(!recommendList.isEmpty())
            prodList = inventoryService.getProductDetails(recommendList.get(0).getId(), null, null);
        return prodList;
    }


    @Override
    public List<RecommendVO> getRecommendCategory(Integer customerId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(basePersonalizeUrl + "recommend")
                .queryParam("customerId", customerId);
        System.out.println(builder.toUriString());
        return restTemplate.exchange(builder.build().toUriString(),HttpMethod.GET, entity, List.class).getBody();
    }

}
