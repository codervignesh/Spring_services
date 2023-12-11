package com.example.customergateway.controller;

//import com.example.customergateway.controller.data.Customer;
import com.example.customergateway.model.vo.CategoryVO;
import com.example.customergateway.model.vo.OrderVO;
import com.example.customergateway.model.vo.ProductVO;
import com.example.customergateway.model.vo.PurchaseOrderVO;
import com.example.customergateway.service.InventoryService;
import com.example.customergateway.service.OrderService;
import com.example.customergateway.service.PersonalizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class HttpMethodController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/inventory/products")
    public List<ProductVO> getProduct(@RequestParam(value = "productId", required = false, defaultValue = "0") Integer productId,
                                      @RequestParam(value = "category", required = false, defaultValue = "null") String category,
                                      @RequestParam(value = "name", required = false, defaultValue = "null") String name){
        return inventoryService.getProductDetails(productId, category, name);
    }

    @PostMapping("/inventory/product")
    public String saveProductDetails(@RequestBody ProductVO p){
        return inventoryService.addProductDetails(p);
    }

    @PutMapping("/inventory/product")
    public String updateProductDetails(@RequestParam(value = "productId") Integer productId, @RequestBody ProductVO p) {
        return inventoryService.updateProductDetails(productId, p);
    }

    @DeleteMapping("/inventory/product")
    public String deleteProductDetails(@RequestParam(value = "productId") Integer productId){
        return inventoryService.deleteProductDetails(productId);
    }

    @GetMapping("/inventory/category")
    public List<CategoryVO> getCategory(){
        return inventoryService.getCategoryDetails();
    }

    @Autowired
    OrderService orderService;

    @GetMapping("/order/orderhistory")
    public List<OrderVO> getOrderHistory(@RequestParam(value = "customerId") String customerId){
        return orderService.getOrderHistory(customerId);
    }

    @PostMapping("/order/place-order")
    public String placeOrder(@RequestBody PurchaseOrderVO ord){
        return orderService.placeOrder(ord);
    }

    @Autowired
    PersonalizeService personalizeService;

    @GetMapping("/personalize/recommendation")
    public List<ProductVO> getRecommendation(@RequestParam(value = "customerId") Integer customerId){
        return personalizeService.getRecommendationList(customerId);
    }

}
