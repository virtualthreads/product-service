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
        log.info("Attempting to create product: {}", request.productName());
        if (request.categoryId() != null && !categoryRepository.existsById(request.categoryId())) {
            log.error("Category not found with ID: {} for product creation", request.categoryId());
            throw new ResourceNotFoundException("Category", String.valueOf(request.categoryId()));
        }
        if (productRepository.existsByProductNameIgnoreCase(request.productName())) {
            log.warn("Product with name {} already exists", request.productName());
            throw new BadRequestException("Product '%s' already exists".formatted(request.productName()));
        }
        log.debug("Creating product with details: name={}, category={}", request.productName(), request.categoryId());
        Product product = productMapper.toEntity(request);
        ProductResponse response = productMapper.toResponse(productRepository.save(product));
        log.info("Successfully created product with ID: {} and name: {}", response.productId(), request.productName());
        return response;
    }

    @Cacheable(value = "Get-product-details", key = "#productId")
    public ProductResponse getProduct(Long productId) {
        log.debug("Fetching product with ID: {}", productId);
        ProductResponse response = productRepository.findById(productId)
                .map(productMapper::toResponseWithVariants)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", productId);
                    return new ResourceNotFoundException("Product", String.valueOf(productId));
                });
        log.info("Successfully retrieved product with ID: {}", productId);
        return response;
    }

    @Cacheable(value = "Get-product-using-category", key = "#categoryId")
    public List<ProductResponse> getProductsByCategory(Long categoryId) {
        log.debug("Fetching products for category ID: {}", categoryId);
        if (!categoryRepository.existsById(categoryId)) {
            log.error("Category not found with ID: {}", categoryId);
            throw new ResourceNotFoundException("Category", String.valueOf(categoryId));
        }
        List<ProductResponse> results = productRepository.findByCategory_categoryId(categoryId)
                .stream()
                .map(productMapper::toResponse)
                .toList();
        log.info("Successfully fetched {} products for category ID: {}", results.size(), categoryId);
        return results;
    }

    public List<ProductResponse> getProductsByBrand(String brand) {
        log.debug("Fetching products for brand: {}", brand);
        List<ProductResponse> results = productRepository.findByBrandIgnoreCase(brand)
                .stream()
                .map(productMapper::toResponse)
                .toList();
        log.info("Successfully fetched {} products for brand: {}", results.size(), brand);
        return results;
    }

    public void deleteProduct(Long productId) {
        log.info("Attempting to delete product ID: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {} for deletion", productId);
                    return new ResourceNotFoundException("Product", String.valueOf(productId));
                });
        productRepository.delete(product);
        log.info("Successfully deleted product ID: {}", productId);
    }

    @Cacheable(value = "product-search", key = "#request")
    public List<ProductResponse> searchProducts(ProductSearchRequest request) {
        log.debug("Searching products with parameters - keyword: {}, brand: {}, color: {}, price range: {} to {}", 
                request.keyword(), request.brand(), request.color(), request.minPrice(), request.maxPrice());

        List<Product> result = productRepository.searchProduct(
                request.keyword(),
                request.brand(),
                request.color(),
                request.minPrice(),
                request.maxPrice()
        );

        log.info("Successfully fetched {} products from database using search filters", result.size());
        return result
                .stream()
                .map(productMapper::toResponseWithVariants)
                .toList();
    }
}