package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.ProductImageCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductImageUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.ProductImageResponseDTO;
import com.aeropelican.productservice.entity.ProductImage;
import com.aeropelican.productservice.entity.ProductVariant;
import com.aeropelican.productservice.exceptions.ProductImageNotFoundException;
import com.aeropelican.productservice.exceptions.ProductVariantNotFoundException;
import com.aeropelican.productservice.mapper.ProductImageMapper;
import com.aeropelican.productservice.repository.ProductImageRepository;
import com.aeropelican.productservice.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductVariantRepository productVariantRepository;

    // =====================================================
    // CREATE IMAGE
    // =====================================================

    public ProductImageResponseDTO createImage(ProductImageCreateRequestDTO request) {

        ProductVariant variant = productVariantRepository.findById(request.getVariantId())
                .orElseThrow(() ->
                        new ProductVariantNotFoundException(
                                "Variant not found with id : " + request.getVariantId()));

        ProductImage image = ProductImage.builder()
                .productVariant(variant)
                .imageUrl(request.getImageUrl())
                .isPrimary(request.getIsPrimary())
                .displayOrder(request.getDisplayOrder())
                .createdAt(LocalDateTime.now())
                .build();

        ProductImage savedImage = productImageRepository.save(image);

        return ProductImageMapper.toResponse(savedImage);
    }

    // =====================================================
    // GET IMAGE BY ID
    // =====================================================

    public ProductImageResponseDTO getImage(Long imageId) {

        ProductImage image = productImageRepository.findById(imageId)
                .orElseThrow(() ->
                        new ProductImageNotFoundException(
                                "Product image not found with id : " + imageId));

        return ProductImageMapper.toResponse(image);
    }

    // =====================================================
    // GET ALL IMAGES OF A VARIANT
    // =====================================================

    public List<ProductImageResponseDTO> getImagesByVariant(Long variantId) {

        return productImageRepository
                .findByProductVariantVariantId(variantId)
                .stream()
                .map(ProductImageMapper::toResponse)
                .toList();
    }

    // =====================================================
    // UPDATE IMAGE
    // =====================================================

    public ProductImageResponseDTO updateImage(
            Long imageId,
            ProductImageUpdateRequestDTO request) {

        ProductImage image = productImageRepository.findById(imageId)
                .orElseThrow(() ->
                        new ProductImageNotFoundException(
                                "Product image not found with id : " + imageId));

        image.setImageUrl(request.getImageUrl());
        image.setIsPrimary(request.getIsPrimary());
        image.setDisplayOrder(request.getDisplayOrder());

        ProductImage updatedImage = productImageRepository.save(image);

        return ProductImageMapper.toResponse(updatedImage);
    }

    // =====================================================
    // DELETE IMAGE
    // =====================================================

    public void deleteImage(Long imageId) {

        ProductImage image = productImageRepository.findById(imageId)
                .orElseThrow(() ->
                        new ProductImageNotFoundException(
                                "Product image not found with id : " + imageId));

        productImageRepository.delete(image);
    }
}