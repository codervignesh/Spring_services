package com.example.customergateway.service;

import com.example.customergateway.model.vo.ProductVO;
import com.example.customergateway.model.vo.RecommendVO;

import java.util.List;

public interface PersonalizeService {
    List<RecommendVO> getRecommendCategory(Integer customerId);

    List<ProductVO> getRecommendationList(Integer customerId);
}
