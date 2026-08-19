package com.aeropelican.productservice.repository;

import com.aeropelican.productservice.entity.ProductVariants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantsRepository extends JpaRepository<ProductVariants, Long> {

    // Find all variants for a given product
    List<ProductVariants> findByProduct_ProductId(Integer productId);

    // Find variants by name if needed
    List<ProductVariants> findByVariantNameIgnoreCase(String variantName);
}