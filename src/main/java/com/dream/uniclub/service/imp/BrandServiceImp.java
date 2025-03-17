package com.dream.uniclub.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.uniclub.dto.BrandDTO;
import com.dream.uniclub.entity.BrandEntity;
import com.dream.uniclub.repository.BrandRepository;
import com.dream.uniclub.service.BrandService;

@Service
public class BrandServiceImp implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<BrandDTO> getAllBrands() {
        List<BrandEntity> brands = brandRepository.findAll();

        List<BrandDTO> brandDTOs = brands.stream().map(item -> {
            BrandDTO brandDTO = new BrandDTO();
            brandDTO.setId(item.getId());
            brandDTO.setName(item.getName());
            return brandDTO;
        }).toList();

        return brandDTOs;
    }

}
