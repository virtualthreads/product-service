package com.aeropelican.productservice.repository;

import com.aeropelican.productservice.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    List<ProductVariant> findByProductId(Long productId);

    Optional<ProductVariant> findByProductIdAndVariantId(Long productId, Long variantId);
}
