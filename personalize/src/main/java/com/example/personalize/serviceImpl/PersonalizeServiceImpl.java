package com.example.personalize.serviceImpl;

import com.example.personalize.api.PersonalizeRepository;
import com.example.personalize.model.entity.Recommend;
import com.example.personalize.model.vo.ProductVO;
import com.example.personalize.model.vo.RecommendVO;
import com.example.personalize.service.PersonalizeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personalService")
public class PersonalizeServiceImpl implements PersonalizeService {
    @Autowired
    PersonalizeRepository personalizeRepository;


    @Override
    public String addCustomerRecommendation(RecommendVO reco){
        ObjectMapper objectMapper = new ObjectMapper();
        personalizeRepository.save(objectMapper.convertValue(reco, Recommend.class));
        return "added";
    }

    @Override
    public List<RecommendVO> getCustomerRecommendation(Integer customerId){
        List<Recommend> recommendation =
                personalizeRepository.findAllById(customerId);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.convertValue(recommendation, List.class));
        return objectMapper.convertValue(recommendation, List.class);
    }

    @KafkaListener(topics = "com.quinbay.product.create", groupId = "group-id")
    public void listen(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductVO p = objectMapper.readValue(message, ProductVO.class);
        System.out.println("received: " + p);
    }


}
