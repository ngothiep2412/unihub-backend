package com.dream.uniclub.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String name;
    private String link;
    private double price;
    private String overview;
    private List<String> categories;
    private List<String> tags;
    private List<SizeDTO> sizes;
    private List<ColorDTO> colors;
}
