package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.response.ProductVariantResponse;
import com.aeropelican.productservice.entity.ProductVariant;
import com.aeropelican.productservice.exceptions.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.ProductVariantMapper;
import com.aeropelican.productservice.repository.ProductRepository;
import com.aeropelican.productservice.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    private final ProductRepository productRepository;
    private final ProductVariantMapper productVariantMapper;

    public List<ProductVariantResponse> getProductVariants(Long productId) {
        log.debug("Fetching all variants for product ID: {}", productId);
        productRepository.findById(productId)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", productId);
                    return new ResourceNotFoundException("Product", String.valueOf(productId));
                });
        List<ProductVariant> variants = productVariantRepository.findByProduct_ProductId(productId);
        log.info("Successfully fetched {} variants for product ID: {}", variants.size(), productId);
        return variants.stream()
                .map(productVariantMapper::toResponse)
                .toList();
    }
    public ProductVariantResponse getProductVariant(Long productId, Long variantId) {
        log.debug("Fetching variant ID: {} for product ID: {}", variantId, productId);
        productRepository.findById(productId)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {} while fetching variant", productId);
                    return new ResourceNotFoundException("Product", "ProductId", String.valueOf(productId));
                });
        ProductVariantResponse response = productVariantRepository.findByProduct_ProductIdAndVariantId(productId, variantId)
                .map(productVariantMapper::toResponse)
                .orElseThrow(() -> {
                    log.error("Variant ID: {} not found for product ID: {}", variantId, productId);
                    return new ResourceNotFoundException("Product Variant", "VariantId", String.valueOf(variantId));
                });
        log.info("Successfully fetched variant ID: {} for product ID: {}", variantId, productId);
        return response;
    }

}
