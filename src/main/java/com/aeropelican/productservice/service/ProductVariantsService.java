package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.PageRequestDTO;
import com.aeropelican.productservice.dto.request.ProductVariantsCreateRequestDTO;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductVariantsResponseDTO;
import com.aeropelican.productservice.dto.response.ProductVariantsUpdateRequestDTO;
import com.aeropelican.productservice.entity.ProductVariants;
import com.aeropelican.productservice.exceptions.BadRequestException;
import com.aeropelican.productservice.exceptions.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.PageResponseMapper;
import com.aeropelican.productservice.mapper.ProductVariantsMapper;
import com.aeropelican.productservice.repository.ProductRepository;
import com.aeropelican.productservice.repository.ProductVariantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantsService {

    private final ProductVariantsRepository productVariantsRepository;
    private final ProductRepository productRepository;

    public PageResponse<ProductVariantsResponseDTO> listVariants(PageRequestDTO requestDTO) {

        Sort sort = requestDTO.getSortDir().equalsIgnoreCase("DESC")
                ? Sort.by(requestDTO.getSortBy()).descending()
                : Sort.by(requestDTO.getSortBy()).ascending();

        Pageable pageable = PageRequest.of(
                requestDTO.getPage(),
                requestDTO.getSize(),
                sort);

        Page<ProductVariants> page =
                productVariantsRepository.findAll(pageable);

        List<ProductVariantsResponseDTO> variants =
                page.getContent()
                        .stream()
                        .map(ProductVariantsMapper::toResponseDTO)
                        .toList();

        return PageResponseMapper.toPageResponse(page, variants);
    }

    public ProductVariantsResponseDTO getVariant(Integer variantId) {

        ProductVariants variant = productVariantsRepository.findById(variantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product Variant",
                                String.valueOf(variantId)));

        return ProductVariantsMapper.toResponseDTO(variant);
    }

    public ProductVariantsResponseDTO createVariant(
            ProductVariantsCreateRequestDTO request) {

        if (!productRepository.existsById(request.getProductId())) {
            throw new ResourceNotFoundException(
                    "Product",
                    String.valueOf(request.getProductId()));
        }

        if (productVariantsRepository.existsBySkuIgnoreCase(request.getSku())) {
            throw new BadRequestException("SKU already exists.");
        }

        ProductVariants variant =
                ProductVariantsMapper.toEntity(request);

        ProductVariants saved =
                productVariantsRepository.save(variant);

        return ProductVariantsMapper.toResponseDTO(saved);
    }

    public ProductVariantsResponseDTO updateVariant(
            Integer variantId,
            ProductVariantsUpdateRequestDTO request) {

        ProductVariants variant =
                productVariantsRepository.findById(variantId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Product Variant",
                                        String.valueOf(variantId)));

        if (!productRepository.existsById(request.getProductId())) {
            throw new ResourceNotFoundException(
                    "Product",
                    String.valueOf(request.getProductId()));
        }

        if (productVariantsRepository
                .existsBySkuIgnoreCaseAndVariantIdNot(
                        request.getSku(),
                        variantId)) {

            throw new BadRequestException("SKU already exists.");
        }

        variant.setProductId(request.getProductId());
        variant.setSku(request.getSku());
        variant.setColor(request.getColor());
        variant.setStorageCapacity(request.getStorageCapacity());
        variant.setPrice(request.getPrice());

        if (request.getIsActive() != null) {
            variant.setIsActive(request.getIsActive());
        }

        ProductVariants updated =
                productVariantsRepository.save(variant);

        return ProductVariantsMapper.toResponseDTO(updated);
    }

    public ProductVariants deleteVariant(Integer variantId) {

        ProductVariants variant =
                productVariantsRepository.findById(variantId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Product Variant",
                                        String.valueOf(variantId)));

        productVariantsRepository.delete(variant);

        return variant;
    }
}