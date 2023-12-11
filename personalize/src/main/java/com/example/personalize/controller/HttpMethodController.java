package com.example.personalize.controller;

import com.example.personalize.model.entity.Recommend;
import com.example.personalize.model.vo.RecommendVO;
import com.example.personalize.service.PersonalizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personalize")
public class HttpMethodController {

    @Autowired
    PersonalizeService personalService;

    @GetMapping("/recommend")
    public List<RecommendVO> getRecommend(@RequestParam(required = false) Integer customerId){
        System.out.println("customerId" + customerId);
        return personalService.getCustomerRecommendation(customerId);
    }

    @PostMapping("/recommend")
    public String addRecommendation(@RequestBody RecommendVO reco) {
        return personalService.addCustomerRecommendation(reco);
    }

    }
