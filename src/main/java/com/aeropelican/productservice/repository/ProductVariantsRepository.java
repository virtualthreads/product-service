package com.aeropelican.productservice.repository;

import com.aeropelican.productservice.entity.ProductVariants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aeropelican.productservice.repository.ProductVariantsRepository;

@Repository
public interface ProductVariantsRepository
        extends JpaRepository<ProductVariants, Integer> {

    boolean existsBySkuIgnoreCase(String sku);

    boolean existsBySkuIgnoreCaseAndVariantIdNot(
            String sku,
            Integer variantId);
}