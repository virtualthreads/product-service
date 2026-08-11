package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.ProductRequest;
import com.aeropelican.productservice.dto.request.ProductSearchRequest;
import com.aeropelican.productservice.dto.response.ProductResponse;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.exceptions.BadRequestException;
import com.aeropelican.productservice.exceptions.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.ProductMapper;
import com.aeropelican.productservice.repository.CategoryRepository;
import com.aeropelican.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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

    @Cacheable(value = "Get-product-details", key = "#productId")
    public ProductResponse getProduct(Long productId) {
        return productRepository.findById(productId)
                .map(productMapper::toResponseWithVariants)
                .orElseThrow(() -> new ResourceNotFoundException("Product", String.valueOf(productId)));
    }

    @Cacheable(value = "Get-product-using-category", key = "#categoryId")
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

    @Cacheable(value = "product-search", key = "#request")
    public List<ProductResponse> searchProducts(ProductSearchRequest request) {
        log.info("Attempting to fetch products from database");

        List<Product> result = productRepository.searchProduct(
                request.keyword(),
                request.brand(),
                request.color(),
                request.minPrice(),
                request.maxPrice()
        );

        log.info("Completed fetching data from the database");
        return result
                .stream()
                .map(productMapper::toResponseWithVariants)
                .toList();
    }
}