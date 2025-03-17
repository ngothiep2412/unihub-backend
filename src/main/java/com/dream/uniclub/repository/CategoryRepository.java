package com.dream.uniclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.uniclub.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

}
