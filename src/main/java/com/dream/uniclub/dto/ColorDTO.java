package com.dream.uniclub.dto;

import java.util.List;

import lombok.Data;

@Data
public class ColorDTO {
    private int id;
    private String name;
    private String images;
    private List<SizeDTO> sizes;
}
