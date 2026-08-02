package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.ProductVariantsCreateRequestDTO;
import com.aeropelican.productservice.dto.request.ProductVariantsUpdateRequestDTO;
import com.aeropelican.productservice.dto.response.Product_VariantsResponseDTO;
import com.aeropelican.productservice.entity.Product_Variants;
import com.aeropelican.productservice.exceptions.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.Product_VariantsMapper;
import com.aeropelican.productservice.repository.Product_VariantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantService {

    private final Product_VariantsRepository productVariantsRepository;

    public List<Product_VariantsResponseDTO> getAllVariants() {

        return productVariantsRepository.findAll()
                .stream()
                .map(Product_VariantsMapper::toResponseDTO)
                .toList();
    }

    public Product_VariantsResponseDTO getVariant(Integer variantId) {

        Product_Variants variant = productVariantsRepository.findById(variantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Variant", String.valueOf(variantId)));

        return Product_VariantsMapper.toResponseDTO(variant);
    }

    public Product_VariantsResponseDTO createVariant(ProductVariantsCreateRequestDTO requestDTO) {

        Product_Variants variant = Product_VariantsMapper.toEntity(requestDTO);

        variant = productVariantsRepository.save(variant);

        return Product_VariantsMapper.toResponseDTO(variant);
    }

    public Product_VariantsResponseDTO updateVariant(Integer variantId,
                                                     ProductVariantsUpdateRequestDTO requestDTO) {

        Product_Variants variant = productVariantsRepository.findById(variantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Variant", String.valueOf(variantId)));

        Product_VariantsMapper.updateEntity(variant, requestDTO);

        variant = productVariantsRepository.save(variant);

        return Product_VariantsMapper.toResponseDTO(variant);
    }

    public Product_Variants deleteVariant(Integer variantId) {

        Product_Variants variant = productVariantsRepository.findById(variantId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product Variant", String.valueOf(variantId)));

        productVariantsRepository.delete(variant);

        return variant;
    }
}