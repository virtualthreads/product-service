package com.aeropelican.productservice.service;

import com.aeropelican.productservice.entity.ProductVariant;
import com.aeropelican.productservice.dto.response.ProductVariantResponseDTO;
import com.aeropelican.productservice.exceptions.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.ProductVariantMapper;
import com.aeropelican.productservice.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductVariantService {

    private final ProductVariantRepository productVariantRepository;

    public ProductVariantResponseDTO getById(Long variantId) {
        ProductVariant productVariant = productVariantRepository.findById(variantId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("ProductVariant", "variantId", variantId)
                );
        return ProductVariantMapper.toResponseDTO(productVariant);
    }
}
