package com.dream.uniclub.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.dream.uniclub.dto.ColorDTO;
import com.dream.uniclub.dto.ProductDTO;
import com.dream.uniclub.dto.SizeDTO;
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

    @Override
    public List<ProductDTO> getProduct(int numPage) {
        // List<ProductEntity> listProductEntities = productRepository.findAll();

        PageRequest page = PageRequest.of(0, numPage);

        List<ProductDTO> listProductDTOs = productRepository.findAll(page).stream().map(item -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(item.getId());
            productDTO.setName(item.getName());
            productDTO.setPrice(item.getPrice());
            // productDTO.setCategory(category);
            if (!item.getVariants().isEmpty()) {
                productDTO.setLink("http://localhost:8080/file/" + item.getVariants().get(0).getImages());
            } else {
                productDTO.setLink("");
            }

            return productDTO;
        }).toList();

        // String categorySql = """
        // SELECT p.id as product_id, c.id as category_id, c.name as category_name
        // FROM product p
        // LEFT JOIN product_category pc ON p.id = pc.id_product
        // LEFT JOIN category c on c.id = pc.id_category
        // """;

        // jdbcTemplate.query(categorySql, (rs) -> {
        // int productId = rs.getInt("product_id");
        // int categoryId = rs.getInt("category_id");

        // if (categoryId != 0) {
        // CategoryEntity category = CategoryEntity.builder()
        // .id(categoryId)
        // .name(rs.getString("category_name"))
        // .build();

        // listProductDTOs.stream().filter(p -> p.getId() == productId)
        // .findFirst()
        // .ifPresent(p -> {
        // System.out.println("Kiểm tra p" + p);
        // if (p.getCategories() == null) {
        // p.setCategories(new ArrayList<>());
        // }

        // if (!p.getCategories().contains(category)) {
        // p.getCategories().add(category);
        // }

        // });
        // }
        // });
        return listProductDTOs;
    }

    @Override
    public ProductDTO getProductById(int id) {
        // ProductDTO productDTO = new ProductDTO();

        // Optional<ProductEntity> optionProductEntity = productRepository.findById(id);
        // if (optionProductEntity.isPresent()) {
        // ProductEntity productEntity = optionProductEntity.get();
        // productDTO.setId(productEntity.getId());
        // productDTO.setName(productEntity.getName());
        // productDTO.setPrice(productEntity.getPrice());
        // if (!productEntity.getVariants().isEmpty()) {
        // productDTO.setLink("http://localhost:8080/file/" +
        // productEntity.getVariants().get(0).getImages());
        // } else {
        // productDTO.setLink("");
        // }
        // }

        Optional<ProductEntity> optionProductEntity = productRepository.findById(id);
        optionProductEntity.stream().map(productEntity -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(productEntity.getId());
            productDTO.setName(productEntity.getName());
            productDTO.setPrice(productEntity.getPrice());
            if (!productEntity.getVariants().isEmpty()) {
                productDTO.setLink("http://localhost:8080/file/" + productEntity.getVariants().get(0).getImages());
            } else {
                productDTO.setLink("");
            }
            productDTO.setCategories(productEntity.getProductCategories().stream()
                    .map(productCategory -> productCategory.getCategory().getName()).toList());

            productDTO.setSizes(productEntity.getVariants().stream().map(variantEntity -> {
                SizeDTO sizeDTO = new SizeDTO();
                sizeDTO.setId(variantEntity.getSize().getId());
                sizeDTO.setName(variantEntity.getSize().getName());

                return sizeDTO;
            }).toList());

            productDTO.setColors(productEntity.getVariants().stream().map(variantEntity -> {
                ColorDTO colorDTO = new ColorDTO();

                colorDTO.setImages(variantEntity.getImages());
                colorDTO.setName(variantEntity.getColor().getName());

                colorDTO.setSizes(productEntity.getVariants().stream().map(variantEntity1 -> {
                    SizeDTO sizeDTO = new SizeDTO();
                    sizeDTO.setId(variantEntity1.getSize().getId());
                    sizeDTO.setName(variantEntity1.getSize().getName());
                    return sizeDTO;
                }).toList());
                return colorDTO;
            }).toList());

            return productDTO;
        }).findFirst().orElseThrow(() -> new RuntimeException("Product not found"));

        return null;
    }

}
