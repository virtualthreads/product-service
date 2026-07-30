package com.aeropelican.productservice.repository;

import com.aeropelican.productservice.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

}
