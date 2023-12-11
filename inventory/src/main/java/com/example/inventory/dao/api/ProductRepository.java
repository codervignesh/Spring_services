package com.example.inventory.dao.api;

import com.example.inventory.model.entity.Product;
import com.example.inventory.model.vo.ProductVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.connection.ReactiveListCommands;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Override
    List<Product> findAll();

    //(:productId is null or c.productId = :name) and
    @Query("SELECT c FROM Product c WHERE ((c.id = :productId) " +
            "OR (c.category.name = :category) " +
            "OR ( c.name = :name)) OR (:name is null AND :category is null AND :productId is null) OR (:name = null AND :category = null AND :productId = 0)")
    List<Product> findAllByIdOrCategoryNameOrName(@Param("productId") Integer productId,
                                                  @Param("category") String category,
                                                  @Param("name") String name);

    @Override
    Product save(Product prod);
}
