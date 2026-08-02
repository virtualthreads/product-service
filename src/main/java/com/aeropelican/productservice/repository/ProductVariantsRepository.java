package com.aeropelican.productservice.repository;

import com.aeropelican.productservice.entity.Product_Variants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface ProductVariantsRepository
            extends JpaRepository<Product_Variants, Integer> {
    }

