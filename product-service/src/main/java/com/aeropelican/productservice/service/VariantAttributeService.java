package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.response.VariantAttributeResponse;
import com.aeropelican.productservice.entity.VariantAttribute;
import com.aeropelican.productservice.exceptions.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.VariantAttributeMapper;
import com.aeropelican.productservice.repository.ProductVariantRepository;
import com.aeropelican.productservice.repository.VariantAttributeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VariantAttributeService {

    private final VariantAttributeRepository variantAttributeRepository;
    private final ProductVariantRepository productVariantRepository;
    private final VariantAttributeMapper variantAttributeMapper;

    public List<VariantAttributeResponse> fetchVariantAttributes(Long variantId) {
        productVariantRepository.findById(variantId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Variant", "VariantId", String.valueOf(variantId))
                );
        return variantAttributeRepository.findByVariantId(variantId)
                .stream()
                .map(variantAttributeMapper::toResponse)
                .toList();
    }
}
