package com.dream.uniclub.dto;

import java.util.List;

import com.dream.uniclub.entity.CategoryEntity;

import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String name;
    private String link;
    private double price;
    private List<CategoryEntity> categories;
}
