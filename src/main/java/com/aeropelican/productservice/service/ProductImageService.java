package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.ProductImageCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductImageUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.ProductImageResponseDTO;
import com.aeropelican.productservice.entity.ProductImage;
import com.aeropelican.productservice.exceptions.ProductImageNotFoundException;
import com.aeropelican.productservice.exceptions.ProductVariantsNotFoundException;
import com.aeropelican.productservice.mapper.ProductImageMapper;
import com.aeropelican.productservice.repository.ProductImageRepository;
import com.aeropelican.productservice.repository.ProductVariantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductVariantsRepository productVariantsRepository;
    private final ProductImageMapper productImageMapper;

    @Transactional(readOnly = true)
    public List<ProductImageResponseDTO> getImagesByVariantId(Long variantId) {
        if (!productVariantsRepository.existsById(variantId)) {
            throw new ProductVariantsNotFoundException("Variant not found with ID: " + variantId);
        }
        return productImageRepository.findByVariantId(variantId).stream()
                .map(productImageMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductImageResponseDTO createImage(ProductImageCreateRequestDTO requestDTO) {
        if (!productVariantsRepository.existsById(requestDTO.getVariantId())) {
            throw new ProductVariantsNotFoundException("Variant not found with ID: " + requestDTO.getVariantId());
        }
        ProductImage image = productImageMapper.toEntity(requestDTO);
        ProductImage savedImage = productImageRepository.save(image);
        return productImageMapper.toResponseDTO(savedImage);
    }

    @Transactional
    public ProductImageResponseDTO updateImage(Long imageId, ProductImageUpdateRequestDTO requestDTO) {
        ProductImage image = productImageRepository.findById(imageId)
                .orElseThrow(() -> new ProductImageNotFoundException("Image not found with ID: " + imageId));

        if (requestDTO.getImageUrl() != null) {
            image.setImageUrl(requestDTO.getImageUrl());
        }
        if (requestDTO.getIsPrimary() != null) {
            image.setIsPrimary(requestDTO.getIsPrimary());
        }
        if (requestDTO.getDisplayOrder() != null) {
            image.setDisplayOrder(requestDTO.getDisplayOrder());
        }

        ProductImage updatedImage = productImageRepository.save(image);
        return productImageMapper.toResponseDTO(updatedImage);
    }

    @Transactional
    public void deleteImage(Long imageId) {
        if (!productImageRepository.existsById(imageId)) {
            throw new ProductImageNotFoundException("Image not found with ID: " + imageId);
        }
        productImageRepository.deleteById(imageId);
    }
}