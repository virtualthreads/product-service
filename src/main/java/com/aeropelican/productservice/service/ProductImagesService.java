package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.PageRequestDTO;
import com.aeropelican.productservice.dto.request.ProductImagesCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductImagesUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductImagesResponseDTO;
import com.aeropelican.productservice.entity.Product_Images;
import com.aeropelican.productservice.exceptions.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.PageResponseMapper;
import com.aeropelican.productservice.mapper.ProductImagesMapper;
import com.aeropelican.productservice.repository.ProductImagesRepository;
import com.aeropelican.productservice.repository.ProductVariantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImagesService {

    private final ProductImagesRepository productImagesRepository;
    private final ProductVariantsRepository productVariantsRepository;

    public PageResponse<ProductImagesResponseDTO> listImages(PageRequestDTO requestDTO) {

        Sort sort = requestDTO.getSortDir().equalsIgnoreCase("DESC")
                ? Sort.by(requestDTO.getSortBy()).descending()
                : Sort.by(requestDTO.getSortBy()).ascending();

        Pageable pageable = PageRequest.of(
                requestDTO.getPage(),
                requestDTO.getSize(),
                sort);

        Page<Product_Images> page =
                productImagesRepository.findAll(pageable);

        List<ProductImagesResponseDTO> images =
                page.getContent()
                        .stream()
                        .map(ProductImagesMapper::toResponseDTO)
                        .toList();

        return PageResponseMapper.toPageResponse(page, images);
    }

    public ProductImagesResponseDTO getImage(Integer imageId) {

        Product_Images image = productImagesRepository.findById(imageId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product Image",
                                String.valueOf(imageId)));

        return ProductImagesMapper.toResponseDTO(image);
    }

    public ProductImagesResponseDTO createImage(
            ProductImagesCreateRequestDTO request) {

        if (!productVariantsRepository.existsById(request.getVariantId())) {
            throw new ResourceNotFoundException(
                    "Product Variant",
                    String.valueOf(request.getVariantId()));
        }

        Product_Images image =
                ProductImagesMapper.toEntity(request);

        Product_Images saved =
                productImagesRepository.save(image);

        return ProductImagesMapper.toResponseDTO(saved);
    }

    public ProductImagesResponseDTO updateImage(
            Integer imageId,
            ProductImagesUpdateRequestDTO request) {

        Product_Images image =
                productImagesRepository.findById(imageId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Product Image",
                                        String.valueOf(imageId)));

        if (!productVariantsRepository.existsById(request.getVariantId())) {
            throw new ResourceNotFoundException(
                    "Product Variant",
                    String.valueOf(request.getVariantId()));
        }

        image.setVariantId(request.getVariantId());
        image.setImageUrl(request.getImageUrl());
        image.setIsPrimary(request.getIsPrimary());
        image.setDisplayOrder(request.getDisplayOrder());

        Product_Images updated =
                productImagesRepository.save(image);

        return ProductImagesMapper.toResponseDTO(updated);
    }

    public void deleteImage(Integer imageId) {

        Product_Images image =
                productImagesRepository.findById(imageId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Product Image",
                                        String.valueOf(imageId)));

        productImagesRepository.delete(image);
    }
}