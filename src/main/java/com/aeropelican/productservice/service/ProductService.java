package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.ProductRequest;
import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.exceptions.BadRequestException;
import com.aeropelican.productservice.exceptions.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.ProductMapper;
import com.aeropelican.productservice.repository.CategoryRepository;
import com.aeropelican.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductResponse createProduct(ProductRequest request) {
        if (request.categoryId() != null && !categoryRepository.existsById(request.categoryId())) {
            throw new ResourceNotFoundException("Category", String.valueOf(request.categoryId()));
        }
        if (productRepository.existsByProductNameIgnoreCase(request.productName())) {
            throw new BadRequestException("Product '%s' already exists".formatted(request.productName()));
        }
        Product product = productMapper.toEntity(request);
        return productMapper.toResponse(productRepository.save(product));
    }

    public ProductResponse getProduct(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper::toResponseWithVariants)
                .orElseThrow(() -> new ResourceNotFoundException("Product", String.valueOf(productId)));
    }

    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Category", String.valueOf(categoryId));
        }
        return productRepository.findByCategory_categoryId(categoryId)
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    public List<ProductResponse> getProductsByBrand(String brand) {
        return productRepository.findByBrandIgnoreCase(brand)
                .stream()
                .map(productMapper::toResponse)
                .toList();
    }

    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", String.valueOf(productId)));
        productRepository.delete(product);
    }
}