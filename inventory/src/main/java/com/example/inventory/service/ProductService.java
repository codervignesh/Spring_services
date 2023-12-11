package com.example.inventory.service;

import com.example.inventory.model.vo.CategoryVO;
import com.example.inventory.model.vo.ProductVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

public interface ProductService {

    List<ProductVO> getProductList(Integer productId, String category, String name);
    List<CategoryVO> getCategoryList();
    String addProduct(ProductVO prod);
    String updateProductDetails(Integer productId, ProductVO p) throws JsonProcessingException;

    void sendPriceDropNotification(com.example.inventory.model.entity.Product prod) throws JsonProcessingException;

    String deleteProduct(Integer productId);


    String getRediCache(String key, String value);
}
