package com.aeropelican.productservice.service;
import com.aeropelican.productservice.dto.request.PageRequestDTO;
import com.aeropelican.productservice.dto.request.ProductImagesCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductImagesUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductImagesResponseDTO;
import com.aeropelican.productservice.entity.Product_Images;
import com.aeropelican.productservice.exceptions.ProductImageNotFound;
import com.aeropelican.productservice.mapper.PageResponseMapper;
import com.aeropelican.productservice.mapper.Product_ImagesMapper;
import com.aeropelican.productservice.repository.ProductImagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class Product_ImagesService {

        private final ProductImagesRepository productImagesRepository;

        // List Images
        public PageResponse<ProductImagesResponseDTO> listProductImages(PageRequestDTO requestDTO) {

            Sort sort = requestDTO.getSortDir().equalsIgnoreCase("DESC")
                    ? Sort.by(requestDTO.getSortBy()).descending()
                    : Sort.by(requestDTO.getSortBy()).ascending();

            Pageable pageable = PageRequest.of(
                    requestDTO.getPage(),
                    requestDTO.getSize(),
                    sort
            );

            Page<Product_Images> pageResults = productImagesRepository.findAll(pageable);

            List<ProductImagesResponseDTO> content = pageResults.getContent()
                    .stream()
                    .map(Product_ImagesMapper::toResponseDTO)
                    .toList();

            return PageResponseMapper.toPageResponse(pageResults, content);
        }

        // Get By Id
        public ProductImagesResponseDTO getProductImage(Long imageId) {

            Product_Images image = productImagesRepository.findById(imageId)
                    .orElseThrow(() -> new ProductImageNotFound("Product Image not found"));

            return Product_ImagesMapper.toResponseDTO(image);
        }

        // Create
        public ProductImagesResponseDTO createProductImage(ProductImagesCreateRequestDTO request) {

            Product_Images image = new Product_Images();

            image.setVariantId(request.getVariantId());
            image.setImageUrl(request.getImageUrl());
            image.setPrimary(request.getIsPrimary());
            image.setDisplayOrder(request.getDisplayOrder());
            image.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            Product_Images saved = productImagesRepository.save(image);

            return Product_ImagesMapper.toResponseDTO(saved);
        }

        // Update
        public ProductImagesResponseDTO updateProductImage(
                Long imageId,
                ProductImagesUpdateRequestDTO request) {

            Product_Images image = productImagesRepository.findById(imageId)
                    .orElseThrow(() -> new ProductImageNotFound("Product Image not found"));

            image.setVariantId(request.getVariantId());
            image.setImageUrl(request.getImageUrl());
            image.setPrimary(request.getIsPrimary());
            image.setDisplayOrder(request.getDisplayOrder());

            Product_Images updated = productImagesRepository.save(image);

            return Product_ImagesMapper.toResponseDTO(updated);
        }

        // Delete
        public void deleteProductImage(Long imageId) {

            Product_Images image = productImagesRepository.findById(imageId)
                    .orElseThrow(() -> new ProductImageNotFound("Product Image not found"));

            productImagesRepository.delete(image);
        }
    }
