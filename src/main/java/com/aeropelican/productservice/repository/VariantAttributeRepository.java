package com.aeropelican.productservice.repository;

import com.aeropelican.productservice.entity.VariantAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VariantAttributeRepository extends JpaRepository<VariantAttribute, Long> {

    List<VariantAttribute> findByProductVariant_VariantId(Long variantId);

    Optional<VariantAttribute> findByProductVariant_VariantIdAndAttrName(Long variantId, String attrName);
}