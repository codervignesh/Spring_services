package com.example.inventory.dao.api;

import com.example.inventory.model.entity.Category;
import com.example.inventory.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Override
    List<Category> findAll();

//    @Query("SELECT c FROM Category c WHERE " +
//            "((c.id = :id) OR (c.name = :name) ) " +
//            "OR (:name is null AND :id is null) " +
//            "OR (:name = null AND :id = 0)")
//    List<Category> findAllByIdOrName(@Param("id") Integer id,
//                                            @Param("name") String name);


    @Override
    Category save(Category prod);



}
