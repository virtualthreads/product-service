package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.response.ProductVariantResponse;
import com.aeropelican.productservice.entity.ProductVariant;
import com.aeropelican.productservice.exceptions.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.ProductVariantMapper;
import com.aeropelican.productservice.repository.ProductRepository;
import com.aeropelican.productservice.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;
    private final ProductVariantMapper productVariantMapper;

    public List<ProductVariantResponse> getProductVariants(Long productId) {
        productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", String.valueOf(productId)));
        List<ProductVariant> variants = productVariantRepository.findByProduct_ProductId(productId);
        return variants.stream()
                .map(productVariantMapper::toResponse)
                .toList();
    }

    public ProductVariantResponse getProductVariant(Long productId, Long variantId) {
        productRepository.findById(productId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product", "ProductId", String.valueOf(productId))
                );
        return productVariantRepository.findByProduct_ProductIdAndVariantId( productId, variantId)
                .map(productVariantMapper::toResponse)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Product Variant", "VariantId", String.valueOf(variantId))
                );
    }

}
