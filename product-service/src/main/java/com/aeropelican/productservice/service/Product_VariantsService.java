package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.PageRequestDTO;
import com.aeropelican.productservice.dto.request.ProductVariantsCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductVariantsUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.Product_VariantsResponseDTO;
import com.aeropelican.productservice.entity.Product_Variants;
import com.aeropelican.productservice.exceptions.ProductVariantNotFound;
import com.aeropelican.productservice.mapper.PageResponseMapper;
import com.aeropelican.productservice.mapper.Product_VariantsMapper;
import com.aeropelican.productservice.repository.ProductVariantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Product_VariantsService {
    private final ProductVariantsRepository productVariantsRepository;


    public PageResponse<Product_VariantsResponseDTO> listProductVariants(PageRequestDTO requestDTO) {

        Sort sort = requestDTO.getSortDir().equalsIgnoreCase("DESC")
                ? Sort.by(requestDTO.getSortBy()).descending()
                : Sort.by(requestDTO.getSortBy()).ascending();

        Pageable pageable = PageRequest.of(
                requestDTO.getPage(),
                requestDTO.getSize(),
                sort
        );

        Page<Product_Variants> pageResults = productVariantsRepository.findAll(pageable);

        List<Product_VariantsResponseDTO> content = pageResults.getContent()
                .stream()
                .map(Product_VariantsMapper::toResponseDTO)
                .toList();

        return PageResponseMapper.toPageResponse(pageResults, content);
    }


    public Product_VariantsResponseDTO getProductVariant(Integer variantId) {

        Product_Variants variant = productVariantsRepository.findById(variantId)
                .orElseThrow(() -> new ProductVariantNotFound("Product Variant not found"));

        return Product_VariantsMapper.toResponseDTO(variant);
    }


    public Product_VariantsResponseDTO createProductVariant(ProductVariantsCreateRequestDTO request) {

        Product_Variants variant = new Product_Variants();

        variant.setProductId(request.getProductId());
        variant.setSku(request.getSku());
        variant.setColor(request.getColor());
        variant.setStorageCapacity(request.getStorageCapacity());
        variant.setPrice(request.getPrice());
        variant.setIsActive(request.getIsActive());

        Timestamp now = new Timestamp(System.currentTimeMillis());
        variant.setCreated_at(now);
        variant.setUpdated_at(now);

        Product_Variants savedVariant = productVariantsRepository.save(variant);

        return Product_VariantsMapper.toResponseDTO(savedVariant);
    }


    public Product_VariantsResponseDTO updateProductVariant(
            Integer variantId,
            ProductVariantsUpdateRequestDTO request) {

        Product_Variants variant = productVariantsRepository.findById(variantId)
                .orElseThrow(() -> new ProductVariantNotFound("Product Variant not found"));

        variant.setProductId(request.getProductId());
        variant.setSku(request.getSku());
        variant.setColor(request.getColor());
        variant.setStorageCapacity(request.getStorageCapacity());
        variant.setPrice(request.getPrice());
        variant.setIsActive(request.getIsActive());
        variant.setUpdated_at(new Timestamp(System.currentTimeMillis()));

        Product_Variants updatedVariant = productVariantsRepository.save(variant);

        return Product_VariantsMapper.toResponseDTO(updatedVariant);
    }


    public void deleteProductVariant(Integer variantId) {

        Product_Variants variant = productVariantsRepository.findById(variantId)
                .orElseThrow(() -> new ProductVariantNotFound("Product Variant not found"));

        productVariantsRepository.delete(variant);
    }
}
