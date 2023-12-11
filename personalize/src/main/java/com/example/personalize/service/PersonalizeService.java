package com.example.personalize.service;

import com.example.personalize.model.vo.RecommendVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personalService")
public interface PersonalizeService {
    public String addCustomerRecommendation(RecommendVO p);

    List<RecommendVO> getCustomerRecommendation(Integer customerId);
}
