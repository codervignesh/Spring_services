package com.example.personalize.api;

import com.example.personalize.model.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalizeRepository extends JpaRepository<Recommend, Integer> {
//    @Override
    List<Recommend> findAllById(Integer customerId);
}
