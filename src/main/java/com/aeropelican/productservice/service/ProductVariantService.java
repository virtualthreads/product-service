package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.ProductVariantsCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductVariantsUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductVariantsResponseDTO;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.entity.ProductVariants;
import com.aeropelican.productservice.exceptions.ProductNotFoundException;
import com.aeropelican.productservice.exceptions.ProductVariantsNotFoundException;
import com.aeropelican.productservice.mapper.PageResponseMapper;
import com.aeropelican.productservice.mapper.ProductVariantsMapper; // <--- ADDED THIS IMPORT
import com.aeropelican.productservice.repository.ProductRepository;
import com.aeropelican.productservice.repository.ProductVariantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductVariantsService {

    private final ProductVariantsRepository productVariantsRepository;
    private final ProductRepository productRepository;

    public ApiResponse<ProductVariantsResponseDTO> saveVariant(ProductVariantsCreateRequestDTO request) {

        ProductVariants variant = new ProductVariants();

        if (request.getProductId() != null) {
            Integer productId = request.getProductId().intValue();
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
            variant.setProduct(product);
        }

        variant.setVariantName(request.getVariantName());
        variant.setColor(request.getColor());
        variant.setSize(request.getSize());

        if (request.getPrice() != null) {
            variant.setPrice(request.getPrice().doubleValue());
        }

        ProductVariants savedVariant = productVariantsRepository.save(variant);
        return ApiResponse.success(ProductVariantsMapper.toProductVariantsResponse(savedVariant), "Product Variant created successfully");
    }

    public ApiResponse<PageResponse<ProductVariantsResponseDTO>> getAllVariants(int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("DESC")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProductVariants> variantPage = productVariantsRepository.findAll(pageable);

        List<ProductVariantsResponseDTO> mappedList = variantPage.getContent()
                .stream()
                .map(ProductVariantsMapper::toProductVariantsResponse)
                .collect(Collectors.toList());

        PageResponse<ProductVariantsResponseDTO> pageResponse = PageResponseMapper.toPageResponse(variantPage, mappedList);

        return ApiResponse.success(pageResponse, "Product Variants retrieved successfully");
    }

    public ApiResponse<ProductVariantsResponseDTO> getVariantById(Long id) {

        ProductVariants variant = productVariantsRepository.findById(id)
                .orElseThrow(() -> new ProductVariantsNotFoundException("Product Variant with ID " + id + " not found"));

        return ApiResponse.success(ProductVariantsMapper.toProductVariantsResponse(variant), "Product Variant retrieved successfully");
    }

    public ApiResponse<ProductVariantsResponseDTO> updateVariant(Long id, ProductVariantsUpdateRequestDTO request) {

        ProductVariants variant = productVariantsRepository.findById(id)
                .orElseThrow(() -> new ProductVariantsNotFoundException("Product Variant Not Found"));

        if (request.getVariantName() != null) {
            variant.setVariantName(request.getVariantName());
        }
        variant.setColor(request.getColor());
        variant.setSize(request.getSize());

        if (request.getPrice() != null) {
            variant.setPrice(request.getPrice().doubleValue());
        }

        ProductVariants updatedVariant = productVariantsRepository.save(variant);
        return ApiResponse.success(ProductVariantsMapper.toProductVariantsResponse(updatedVariant), "Product Variant updated successfully");
    }

    public ApiResponse<String> deleteVariant(Long id) {

        if (!productVariantsRepository.existsById(id)) {
            throw new ProductVariantsNotFoundException("Product Variant with ID " + id + " not found");
        }

        productVariantsRepository.deleteById(id);

        return ApiResponse.success(null, "Product Variant Deleted Successfully");
    }
}