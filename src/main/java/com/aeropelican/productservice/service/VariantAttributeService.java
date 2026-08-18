package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.response.VariantAttributeResponse;
import com.aeropelican.productservice.entity.VariantAttribute;
import com.aeropelican.productservice.exceptions.ResourceNotFoundException;
import com.aeropelican.productservice.mapper.VariantAttributeMapper;
import com.aeropelican.productservice.repository.ProductVariantRepository;
import com.aeropelican.productservice.repository.VariantAttributeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VariantAttributeService {

    private final VariantAttributeRepository variantAttributeRepository;
    private final ProductVariantRepository productVariantRepository;
    private final VariantAttributeMapper variantAttributeMapper;

    public List<VariantAttributeResponse> fetchVariantAttributes(Long variantId) {
        log.debug("Fetching attributes for variant ID: {}", variantId);
        productVariantRepository.findById(variantId)
                .orElseThrow(() -> {
                    log.error("Variant not found with ID: {}", variantId);
                    return new ResourceNotFoundException("Variant", "VariantId", String.valueOf(variantId));
                });
        List<VariantAttributeResponse> attributes = variantAttributeRepository.findByVariantId(variantId)
                .stream()
                .map(variantAttributeMapper::toResponse)
                .toList();
        log.info("Successfully fetched {} attributes for variant ID: {}", attributes.size(), variantId);
        return attributes;
    }
}
