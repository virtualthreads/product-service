package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.ProductCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductResponseDTO;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.exceptions.BadRequestException;
import com.aeropelican.productservice.exceptions.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.PageResponseMapper;
import com.aeropelican.productservice.mapper.ProductMapper;
import com.aeropelican.productservice.repository.CategoryRepository;
import com.aeropelican.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public PageResponse<ProductResponseDTO> listProducts(int page, int size, String sortBy, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("DESC")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> pageResult = productRepository.findAll(pageable);

        List<ProductResponseDTO> content = pageResult.stream()
                .map(ProductMapper::toResponseDTO)
                .toList();
        return PageResponseMapper.toPageResponse(pageResult, content);
    }

    public ProductResponseDTO getProduct(Integer productId) {
        System.out.println("Attempting to fetch product with ID: " + productId);
        return productRepository.findById(productId)
                .map(ProductMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Product is not found for provided ID: " + productId));
    }


    public ProductResponseDTO createProduct(ProductCreateRequestDTO request) {

        String productName = request.getProductName().trim();

        if (productRepository.existsByProductNameIgnoreCase(productName)) {
            throw new BadRequestException("Product already exists.");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category", String.valueOf(request.getCategoryId()))
                );

        Product product = ProductMapper.toEntity(request);
        //TODO: set category ID if present
        //product.setCategoryId(request.getCategoryId());
        return ProductMapper.toResponseDTO(productRepository.save(product));
    }

    public ProductResponseDTO updateProduct(Integer id, ProductUpdateRequestDTO request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", String.valueOf(id)));

        String productName = request.getProductName().trim();

        if (productRepository.existsByProductNameIgnoreCaseAndProductIdNot(productName, id)) {
            throw new BadRequestException("Product name already exists.");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category", String.valueOf(request.getCategoryId()))
                );

        //TODO: Set category ID if present
        //product.setCategory(category);
        product.setProductName(productName);
        product.setDescription(request.getDescription());
        product.setBrand(request.getBrand());

        if (request.getIsActive() != null) {
            product.setIsActive(request.getIsActive());
        }

        return ProductMapper.toResponseDTO(productRepository.save(product));
    }
}
