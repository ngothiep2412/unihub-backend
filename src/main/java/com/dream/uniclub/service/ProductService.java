package com.dream.uniclub.service;

import java.util.List;

import com.dream.uniclub.dto.ProductDTO;
import com.dream.uniclub.request.AddProductRequest;

public interface ProductService {
    public void addProduct(AddProductRequest addProductRequest);

    List<ProductDTO> getProduct(int numPage);
}
