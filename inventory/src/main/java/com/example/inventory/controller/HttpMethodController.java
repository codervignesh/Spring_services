package com.example.inventory.controller;

import com.example.inventory.model.entity.Product;
import com.example.inventory.model.vo.CategoryVO;
import com.example.inventory.model.vo.ProductVO;
import com.example.inventory.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class HttpMethodController {

    @Autowired
    ProductService prodService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/products")
    public List<ProductVO> getProductByProducts(@RequestParam(required = false) Integer productId, @RequestParam(required = false) String category, @RequestParam(required = false) String name){
        return prodService.getProductList(productId, category, name);
    }

    @PostMapping("/product")
    public String addProductDetails(@RequestBody ProductVO p) {
        return prodService.addProduct(p);
    }

    @PutMapping("/product")
    public String updateProductDetails(@RequestParam(value = "productId") Integer productId, @RequestBody ProductVO p) throws JsonProcessingException {
        return prodService.updateProductDetails(productId, p);
    }

    @DeleteMapping("/product")
    public String deleteProductDetails(@RequestParam(value = "productId") Integer productId){
        return prodService.deleteProduct(productId);
    }

    @GetMapping("/category")
    public List<CategoryVO> getProductByCategory(){
        return prodService.getCategoryList();
    }

    @PostMapping("/publish")    //testing kafka
    public void publishKafka(@RequestBody Product prod) throws JsonProcessingException {
        prodService.sendPriceDropNotification(prod);
    }

    @GetMapping("/rediscache")
    public String getRedisCache(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
        return prodService.getRediCache(key,value);
    }

}
