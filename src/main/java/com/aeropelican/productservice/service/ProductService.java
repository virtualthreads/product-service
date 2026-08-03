package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.PageRequestDTO;
import com.aeropelican.productservice.dto.request.ProductCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductResponseDTO;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.exceptions.BadRequestException;
import com.aeropelican.productservice.exceptions.ProductNotFound;
import com.aeropelican.productservice.exceptions.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.PageResponseMapper;
import com.aeropelican.productservice.mapper.ProductMapper;
import com.aeropelican.productservice.repository.CategoryRepository;
import com.aeropelican.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public PageResponse<ProductResponseDTO> listProducts(PageRequestDTO requestDTO) {

        Sort sort = requestDTO.getSortDir().equalsIgnoreCase("DESC")
                ? Sort.by(requestDTO.getSortBy()).descending()
                : Sort.by(requestDTO.getSortBy()).ascending();

        Pageable pageable = PageRequest.of(
                requestDTO.getPage(),
                requestDTO.getSize(),
                sort
        );

        Page<Product> page = productRepository.findAll(pageable);

        List<ProductResponseDTO> products = page.getContent()
                .stream()
                .map(ProductMapper::toResponseDTO)
                .toList();

        return PageResponseMapper.toPageResponse(page, products);
    }


    public ProductResponseDTO getProduct(Integer productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product",
                                String.valueOf(productId)));

        return ProductMapper.toResponseDTO(product);
    }


    public ProductResponseDTO createProduct(ProductCreateRequestDTO request) {

        String productName = request.getProductName().trim();

        if (productRepository.existsByProductNameIgnoreCase(productName)) {
            throw new BadRequestException("Product already exists.");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category",
                                String.valueOf(request.getCategoryId())));

        Product product = ProductMapper.toEntity(request);

        // IMPORTANT
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        return ProductMapper.toResponseDTO(savedProduct);
    }


    public ProductResponseDTO updateProduct(
            Integer productId,
            ProductUpdateRequestDTO request) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product",
                                String.valueOf(productId)));

        String productName = request.getProductName().trim();

        if (productRepository.existsByProductNameIgnoreCaseAndProductIdNot(
                productName,
                productId)) {

            throw new BadRequestException("Product name already exists.");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category",
                                String.valueOf(request.getCategoryId())));

        product.setCategory(category);
        product.setProductName(productName);
        product.setDescription(request.getDescription());
        product.setBrand(request.getBrand());

        if (request.getIsActive() != null) {
            product.setIsActive(request.getIsActive());
        }

        Product updatedProduct = productRepository.save(product);

        return ProductMapper.toResponseDTO(updatedProduct);
    }


    public Product deleteProduct(Integer productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFound("Product not found"));

        productRepository.delete(product);

        return product;
    }
}