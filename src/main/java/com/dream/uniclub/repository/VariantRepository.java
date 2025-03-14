package com.dream.uniclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dream.uniclub.entity.VariantEntity;

@Repository
public interface VariantRepository extends JpaRepository<VariantEntity, Integer> {

}
