package com.aeropelican.productservice.repository;

import com.aeropelican.productservice.entity.VariantAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantAttributeRepository extends JpaRepository<VariantAttribute, Long> {
    List<VariantAttribute> findByVariantId(Long variantId);
}
