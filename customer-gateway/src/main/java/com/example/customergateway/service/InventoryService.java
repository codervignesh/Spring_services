package com.example.customergateway.service;

import com.example.customergateway.model.vo.CategoryVO;
import com.example.customergateway.model.vo.ProductVO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface InventoryService {

    List<ProductVO> getProductDetails(Integer productId, String category, String name);

    String addProductDetails(ProductVO p);

    String updateProductDetails(Integer productId, ProductVO p);

    String deleteProductDetails(Integer productId);


    void sendMessage(ProductVO prod) throws JsonProcessingException;

    List<CategoryVO> getCategoryDetails();
}
