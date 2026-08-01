package com.aeropelican.productservice.repository;

import com.aeropelican.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByProductNameIgnoreCase(String productName);
    boolean existsByProductNameIgnoreCaseAndProductIdNot(String productName, Long productId);

    List<Product> findByCategoryId(Long categoryId);
    List<Product> findByBrandIgnoreCase(String brand);
}