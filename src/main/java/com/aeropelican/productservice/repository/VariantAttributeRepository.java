package com.aeropelican.productservice.repository;

import com.aeropelican.productservice.entity.VariantAttribute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantAttributeRepository extends JpaRepository<VariantAttribute, Long> {

    /**
     * Get all attributes for a particular variant.
     */
    List<VariantAttribute> findByVariantId(Long variantId);

    /**
     * Get paginated attributes for a variant.
     */
    Page<VariantAttribute> findByVariantId(Long variantId, Pageable pageable);

    /**
     * Check if an attribute already exists for a variant.
     */
    boolean existsByVariantIdAndAttrName(Long variantId, String attrName);

    /**
     * Delete all attributes of a variant.
     */
    void deleteByVariantId(Long variantId);

    /**
     * Find attributes by name.
     */
    List<VariantAttribute> findByAttrName(String attrName);

    /**
     * Find attributes by name and value.
     */
    List<VariantAttribute> findByAttrNameAndAttrValue(String attrName, String attrValue);
}