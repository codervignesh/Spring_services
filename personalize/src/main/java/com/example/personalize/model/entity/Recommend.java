package com.example.personalize.model.entity;

import com.example.personalize.model.constant.FieldNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = FieldNames.CUSTOMER_T)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recommend {
    @Id
    @Column(name = FieldNames.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = FieldNames.CATEGORY_ID, nullable  = false)
    Long categoryId;
}
