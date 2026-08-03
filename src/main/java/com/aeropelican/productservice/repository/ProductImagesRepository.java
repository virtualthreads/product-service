package com.aeropelican.productservice.repository;

import com.aeropelican.productservice.entity.Product_Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImagesRepository
        extends JpaRepository<Product_Images, Integer> {
}