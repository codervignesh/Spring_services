package com.example.inventory.serviceImpl;

import com.example.inventory.dao.api.CategoryRepository;
import com.example.inventory.dao.api.ProductRepository;
import com.example.inventory.model.entity.Category;
import com.example.inventory.model.entity.Product;
import com.example.inventory.model.vo.CategoryVO;
import com.example.inventory.model.vo.ProductVO;
import com.example.inventory.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("prodService")
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    @Cacheable(value = "products")
    public List<ProductVO> getProductList(Integer productId, String category, String name) {
        List<Product> productList =
                productRepository.findAllByIdOrCategoryNameOrName(productId, category, name);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(productList, List.class);
    }

    @Override
    public List<CategoryVO> getCategoryList() {
        List<Category> categoryList =
                categoryRepository.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(categoryList, List.class);
    }

    @Override
    public String addProduct(ProductVO prod) {
        ObjectMapper objectMapper = new ObjectMapper();
        productRepository.save(objectMapper.convertValue(prod, com.example.inventory.model.entity.Product.class));
        return "added";
    }

    @Transactional
    @Override
    public String updateProductDetails(Integer productId, ProductVO p) throws JsonProcessingException {
        Boolean priceDrop = false;
        Optional<Product> optionalEntity = productRepository.findById(productId);

        if (optionalEntity.isPresent()) {
            Product existingEntity = optionalEntity.get();

            existingEntity.setQuantity(p.getQuantity());
            existingEntity.setName(p.getName());

            if (existingEntity.getPrice() > p.getPrice())
                priceDrop = true;

            existingEntity.setPrice(p.getPrice());
            productRepository.save(existingEntity);

            if (priceDrop)
                sendPriceDropNotification(existingEntity);
            return "Entity with id " + productId + " updated successfully";
        } else {
            return "Entity with id " + productId + " not found";
        }
    }

    @Override
    public void sendPriceDropNotification(Product prod) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        kafkaTemplate.send("com.quinbay.product.create", objectMapper.writeValueAsString(prod));
    }


    @Override
    public String deleteProduct(Integer productId) {
        Optional<Product> optionalEntity = productRepository.findById(productId);

        if (optionalEntity.isPresent()) {
            productRepository.deleteById(productId);
            return "product deleted successfully";
        } else
            return "product with id: " + productId + " not present";
    }


//    @KafkaListener(topics = "com.quinbay.product.create", groupId = "group-id")
//    public void listen(String message) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        Product p = objectMapper.readValue(message, Product.class);
//        System.out.println(p.getId());
//    }

    @Override
    @Cacheable(value = "springBootTraining", key = "#key")
    public String getRediCache(String key, String value) {
        return value;
    }
}

