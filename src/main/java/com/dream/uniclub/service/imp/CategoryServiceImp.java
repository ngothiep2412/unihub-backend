package com.dream.uniclub.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.uniclub.entity.CategoryEntity;
import com.dream.uniclub.repository.CategoryRepository;
import com.dream.uniclub.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getCategories() {

        return categoryRepository.findAll();
    }

}
