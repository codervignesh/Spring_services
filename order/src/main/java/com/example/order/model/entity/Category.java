package com.example.order.model.entity;


//import com.example.inventory.model.constant.FieldNames;
import com.example.order.model.VO.ProductVO;
import com.example.order.model.constant.FieldNames;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = FieldNames.CATEGORY_T)
//@Data
@NoArgsConstructor
@AllArgsConstructor

public class Category {
    @Id
//    @Column(name = FieldNames.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = FieldNames.NAME, nullable  = false)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<ProductVO> product = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
