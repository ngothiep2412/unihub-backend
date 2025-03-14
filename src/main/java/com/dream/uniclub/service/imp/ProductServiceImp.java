package com.dream.uniclub.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.uniclub.entity.BrandEntity;
import com.dream.uniclub.entity.ColorEntity;
import com.dream.uniclub.entity.ProductEntity;
import com.dream.uniclub.entity.SizeEntity;
import com.dream.uniclub.entity.VariantEntity;
import com.dream.uniclub.repository.ProductRepository;
import com.dream.uniclub.repository.VariantRepository;
import com.dream.uniclub.request.AddProductRequest;
import com.dream.uniclub.service.FileService;
import com.dream.uniclub.service.ProductService;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    VariantRepository variantRepository;

    @Autowired
    FileService fileService;

    @Transactional
    @Override
    public void addProduct(AddProductRequest addProductRequest) {
        ProductEntity product = new ProductEntity();

        product.setName(addProductRequest.name());
        product.setDesc(addProductRequest.desc());
        product.setInfo(addProductRequest.information());
        product.setPrice(addProductRequest.price());

        BrandEntity brand = new BrandEntity();
        brand.setId(addProductRequest.idBrand());

        product.setBrand(brand);
        ProductEntity productInserted = productRepository.save(product);

        VariantEntity variantEntity = new VariantEntity();
        variantEntity.setProduct(productInserted);

        ColorEntity colorEntity = new ColorEntity();
        colorEntity.setId(addProductRequest.idColor());
        variantEntity.setColor(colorEntity);

        SizeEntity sizeEntity = new SizeEntity();
        sizeEntity.setId(addProductRequest.idSize());
        variantEntity.setSize(sizeEntity);
        variantEntity.setPrice(addProductRequest.priceSize());
        variantEntity.setQuantity(addProductRequest.quantity());
        variantEntity.setImages(addProductRequest.files().getOriginalFilename());

        variantRepository.save(variantEntity);

        fileService.saveFile(addProductRequest.files());
    }
}
